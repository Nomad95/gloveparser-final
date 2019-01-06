package org.politechnika.analysis.impl;

import org.politechnika.analysis.StatisticsAnalyzer;
import org.politechnika.analysis.utils.ChartData;
import org.politechnika.analysis.utils.GloveGrouper;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Chart data jest wartością zwracaną potrzebną do wykonania odpowiednich wykresów.
 * Wywołanie poszczególnych metod powinno odbywać się po kolei, gdyż każda kolejna metoda potrzebuje wyników poprzedniej
 */
public class GloveStatisticsAnalyzerImpl implements StatisticsAnalyzer<GloveDataDto> {

    @Override
    public List<GloveDataDto> averageDataInOneSensor(List<GloveDataDto> dtos) {
        Map<GloveGrouper, List<GloveDataDto>> data = dtos.stream()
                .collect(Collectors.groupingBy(dto ->
                        new GloveGrouper(dto.getHand(), dto.getSensorNumber(), dto.getTimestamp().getEpochSecond()), Collectors.toList()));
        List<GloveDataDto> averageData = new ArrayList<>();
        data.forEach((k, v) -> averageData.add(calculateAverageData(k,v)));
        averageData.sort(Comparator.comparing(GloveDataDto::getTimestamp));
        return averageData;
    }

    private GloveDataDto calculateAverageData(GloveGrouper k, List<GloveDataDto> dataPerSec) {
        return GloveDataDto.builder()
                .hand(k.getHand())
                .scale(getAverageValue(dataPerSec, GloveDataDto::getScale))
                .raw((int) getAverageValue(dataPerSec, GloveDataDto::getRaw))
                .low((int) getAverageValue(dataPerSec, GloveDataDto::getLow))
                .up((int) getAverageValue(dataPerSec, GloveDataDto::getUp))
                .timestamp(Instant.ofEpochSecond(k.getEpochSeconds()))
                .build();
    }

    private double getAverageValue(List<GloveDataDto> dataPerSec, ToDoubleFunction<GloveDataDto> func) {
        return (int) dataPerSec.stream()
                .mapToDouble(func).average().getAsDouble();
    }

    @Override
    public ChartData averageOneFingerEvery1secFromHand(List<GloveDataDto> groupedData, String hand) {
        long firstMove = Optional.ofNullable(groupedData.get(0))
                .get()
                .getTimestamp().getEpochSecond();
        Map<GloveGrouper, List<GloveDataDto>> data = groupedData.stream()
                .filter(dto -> hand.equals(dto.getHand()))
                .collect(Collectors.groupingBy(dto ->
                        new GloveGrouper(dto.getHand(), dto.getTimestamp().getEpochSecond()), Collectors.toList()));
        AtomicInteger index = new AtomicInteger();
        ChartData chartData = new ChartData(data.size());
        data.forEach((k,v) -> {
                calculateAverageAndInsertData(index, chartData, v, 0, 1);
                calculateAverageAndInsertData(index, chartData, v, 3, 4);
                calculateAverageAndInsertData(index, chartData, v, 6, 7);
                calculateAverageAndInsertData(index, chartData, v, 9, 10);
                calculateAverageAndInsertData(index, chartData, v, 12, 13);
                chartData.setTimeElem(index.getAndIncrement(), getTime(firstMove, k.getEpochSeconds()));
        });
        return chartData;
    }

    private double getTime(long firstMove, long epochSeconds) {
        return (double) epochSeconds - firstMove;
    }

    private void calculateAverageAndInsertData(AtomicInteger index, ChartData chartData, List<GloveDataDto> gloveData, int sensor1, int sensor2) {
        OptionalDouble average = gloveData.stream()
                .filter(row -> row.getSensorNumber() == sensor1 || row.getSensorNumber() == sensor2)
                .mapToDouble(row -> row.getRaw())
                .average();
        if (average.isPresent()) {
            switch (sensor1) {
                case 0: chartData.setFinger1Elem(index.get(), average.getAsDouble()); break;
                case 3: chartData.setFinger2Elem(index.get(), average.getAsDouble()); break;
                case 6: chartData.setFinger3Elem(index.get(), average.getAsDouble()); break;
                case 9: chartData.setFinger4Elem(index.get(), average.getAsDouble()); break;
                case 12: chartData.setFinger5Elem(index.get(), average.getAsDouble()); break;
                default: break;
            }
        }
    }

    @Override
    public ChartData varianceOneFingerEvery1secFromHand(List<GloveDataDto> dtos, ChartData average, String hand) {
        long firstMove = Optional.ofNullable(dtos.get(0))
                .get()
                .getTimestamp().getEpochSecond();
        Map<GloveGrouper, List<GloveDataDto>> data = dtos.stream()
                .filter(dto -> hand.equals(dto.getHand()))
                .collect(Collectors.groupingBy(dto ->
                        new GloveGrouper(dto.getHand(), dto.getTimestamp().getEpochSecond()), Collectors.toList()));
        AtomicInteger index = new AtomicInteger();
        ChartData chartData = new ChartData(data.size());
        data.forEach((k,v) -> {
            calculateVarianceAndInsertData(average, index.get(), chartData, v, 0, 1);
            calculateVarianceAndInsertData(average, index.get(), chartData, v, 3, 4);
            calculateVarianceAndInsertData(average, index.get(), chartData, v, 6, 7);
            calculateVarianceAndInsertData(average, index.get(), chartData, v, 9, 10);
            calculateVarianceAndInsertData(average, index.get(), chartData, v, 12, 13);
            chartData.setTimeElem(index.getAndIncrement(), getTime(firstMove, k.getEpochSeconds()));
        });
        return chartData;
    }

    private void calculateVarianceAndInsertData(ChartData average, int index, ChartData chartData, List<GloveDataDto> gloveData, int sensor1, int sensor2) {
        OptionalDouble variancy = gloveData.stream()
                .filter(row -> row.getSensorNumber() == sensor1 || row.getSensorNumber() == sensor2)
                .mapToDouble(row -> calculateSquareOfDifference(index, average, row.getRaw(), sensor1))
                .average();
        if (variancy.isPresent()) {
            switch (sensor1) {
                case 0: chartData.setFinger1Elem(index, variancy.getAsDouble()); break;
                case 3: chartData.setFinger2Elem(index, variancy.getAsDouble()); break;
                case 6: chartData.setFinger3Elem(index, variancy.getAsDouble()); break;
                case 9: chartData.setFinger4Elem(index, variancy.getAsDouble()); break;
                case 12: chartData.setFinger5Elem(index, variancy.getAsDouble()); break;
                default: break;
            }
        }
    }

    private double calculateSquareOfDifference(int index, ChartData average, double raw, int sensorNumber) {
        switch (sensorNumber) {
            case 0: return Math.pow(raw - average.getFinger1Elem(index), 2);
            case 3: return Math.pow(raw - average.getFinger2Elem(index), 2);
            case 6: return Math.pow(raw - average.getFinger3Elem(index), 2);
            case 9: return Math.pow(raw - average.getFinger4Elem(index), 2);
            case 12: return Math.pow(raw - average.getFinger5Elem(index), 2);
            default: return 0;
        }
    }

    @Override
    public ChartData calculateAverageDataFromFingers(ChartData dataFor1s) {
        ChartData chartData = new ChartData(1);
        chartData.setTimeElem(0, 0);
        chartData.setFinger1Elem(0, calculateAverageDataForFinger(dataFor1s, 1));
        chartData.setFinger2Elem(0, calculateAverageDataForFinger(dataFor1s, 2));
        chartData.setFinger3Elem(0, calculateAverageDataForFinger(dataFor1s, 3));
        chartData.setFinger4Elem(0, calculateAverageDataForFinger(dataFor1s, 4));
        chartData.setFinger5Elem(0, calculateAverageDataForFinger(dataFor1s, 5));
        return chartData;
    }

    private double calculateAverageDataForFinger(ChartData variance, int fingerNumber) {
        switch (fingerNumber) {
            case 1: return Arrays.stream(requireNonNull(variance.getFinger1())).average().orElse(0);
            case 2: return Arrays.stream(requireNonNull(variance.getFinger2())).average().orElse(0);
            case 3: return Arrays.stream(requireNonNull(variance.getFinger3())).average().orElse(0);
            case 4: return Arrays.stream(requireNonNull(variance.getFinger4())).average().orElse(0);
            case 5: return Arrays.stream(requireNonNull(variance.getFinger5())).average().orElse(0);
            default: return 0;
        }
    }

    @Override
    public ChartData standardDeviationEveryFingerEvery1sec(ChartData varianceEvery1s) {
        int arraySize = varianceEvery1s.getTime().length;
        ChartData chartData = new ChartData(arraySize);
        for (int i = 0; i < arraySize; i++) {
            chartData.setTimeElem(i, varianceEvery1s.getTimeElem(i));
            chartData.setFinger1Elem(i, calculateStandardDeviation(varianceEvery1s.getFinger1Elem(i)));
            chartData.setFinger2Elem(i, calculateStandardDeviation(varianceEvery1s.getFinger2Elem(i)));
            chartData.setFinger3Elem(i, calculateStandardDeviation(varianceEvery1s.getFinger3Elem(i)));
            chartData.setFinger4Elem(i, calculateStandardDeviation(varianceEvery1s.getFinger4Elem(i)));
            chartData.setFinger5Elem(i, calculateStandardDeviation(varianceEvery1s.getFinger5Elem(i)));
        }
        return chartData;
    }

    private double calculateStandardDeviation(double fingerElem) {
        return Math.sqrt(fingerElem);
    }
}
