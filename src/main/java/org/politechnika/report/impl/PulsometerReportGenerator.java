package org.politechnika.report.impl;

import org.politechnika.analysis.impl.PulsometerStatisticAnalyzerImpl;
import org.politechnika.commons.Constants;
import org.politechnika.data_parser.csv.definitions.PulsometerParsingStrategy;
import org.politechnika.data_parser.csv.definitions.beans.PulsometerDataDto;
import org.politechnika.data_parser.csv.impl.BeanCsvParser;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.report.ReportGenerator;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.DoubleStream;

import static java.util.stream.Collectors.groupingBy;
import static org.politechnika.frontend.main_controller.MainController.getTimeIntervalMillis;

public class PulsometerReportGenerator implements ReportGenerator {
    @Override
    public void generate(AbstractDataFile dataFile) {

        BeanCsvParser beanCsvParser = new BeanCsvParser();
        List<PulsometerDataDto> pulsometerDataDtos;
        try {
            pulsometerDataDtos = beanCsvParser.parseToBean(dataFile, new PulsometerParsingStrategy());
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }

        PulsometerStatisticAnalyzerImpl analyzer = new PulsometerStatisticAnalyzerImpl();
//        double average = analyzer.getAverage(pulsometerDataDtos, PulsometerDataDto::getValue);
//        double kurtosis = analyzer.getKurtosis(pulsometerDataDtos, PulsometerDataDto::getValue);
//        double skewness = analyzer.getSkewness(pulsometerDataDtos, PulsometerDataDto::getValue);
//        double deviation = analyzer.getStandardDeviation(pulsometerDataDtos, PulsometerDataDto::getValue);
//        double variance = analyzer.getVariance(pulsometerDataDtos, PulsometerDataDto::getValue);

        Map<Integer, List<PulsometerDataDto>> timeSegmentedStats =
                pulsometerDataDtos.stream()
                                  .collect(groupingBy(time -> {
                                      return (time.getTime().toSecondOfDay() * 1000) / getTimeIntervalMillis();
                                  }));

        Set<Integer> keys = timeSegmentedStats.keySet();
        generateAvgChart(analyzer, timeSegmentedStats, keys);
        generateKurtosisChart(analyzer, timeSegmentedStats, keys);
        generateSkewnessChart(analyzer, timeSegmentedStats, keys);
        generateStdDeviationChart(analyzer, timeSegmentedStats, keys);
        generateVarianceChart(analyzer, timeSegmentedStats, keys);
    }


    private void generateVarianceChart(PulsometerStatisticAnalyzerImpl analyzer, Map<Integer, List<PulsometerDataDto>> timeSegmentedStats, Set<Integer> keys) {
        double[] timeSegVariance = keys.stream().mapToDouble(key -> analyzer.getVariance(timeSegmentedStats.get(key), PulsometerDataDto::getValue)).toArray();
        double[] timeSeries = DoubleStream.iterate(0, n -> n + getTimeIntervalMillis()).limit(timeSegVariance.length).toArray();
        new ChartGeneratorImpl().drawChart(new Plot.Builder(new Object[]{timeSegVariance}, timeSeries)
                .withFileName("puls-chart-variance")
                .withGrid()
                .withLegend("{'Wariancja'}")
                .withTitle("Pulsometr - wariancja")
                .withXAxisName("Czas")
                .withYAxisName("Wartość")
                .build(MainController.getDestinationSubFolder()));
    }

    private void generateStdDeviationChart(PulsometerStatisticAnalyzerImpl analyzer, Map<Integer, List<PulsometerDataDto>> timeSegmentedStats, Set<Integer> keys) {
        double[] timeSegDeviation = keys.stream().mapToDouble(key -> analyzer.getStandardDeviation(timeSegmentedStats.get(key), PulsometerDataDto::getValue)).toArray();
        double[] timeSeries = DoubleStream.iterate(0, n -> n + getTimeIntervalMillis()).limit(timeSegDeviation.length).toArray();
        new ChartGeneratorImpl().drawChart(new Plot.Builder(new Object[]{timeSegDeviation}, timeSeries)
                .withFileName("puls-chart-std-deviation")
                .withGrid()
                .withLegend("{'Dewiacja standardowa'}")
                .withTitle("Pulsometr - dewiacja standardowa")
                .withXAxisName("Czas")
                .withYAxisName("Wartość")
                .build(MainController.getDestinationSubFolder()));
    }

    private void generateSkewnessChart(PulsometerStatisticAnalyzerImpl analyzer, Map<Integer, List<PulsometerDataDto>> timeSegmentedStats, Set<Integer> keys) {
        double[] timeSegSkewness = keys.stream().mapToDouble(key -> analyzer.getSkewness(timeSegmentedStats.get(key), PulsometerDataDto::getValue)).toArray();
        double[] timeSeries = DoubleStream.iterate(0, n -> n + getTimeIntervalMillis()).limit(timeSegSkewness.length).toArray();
        new ChartGeneratorImpl().drawChart(new Plot.Builder(new Object[]{timeSegSkewness}, timeSeries)
                .withFileName("puls-chart-skewness")
                .withGrid()
                .withLegend("{'Skośność'}")
                .withTitle("Pulsometr - skośność")
                .withXAxisName("Czas")
                .withYAxisName("Wartość")
                .build(MainController.getDestinationSubFolder()));
    }

    private void generateKurtosisChart(PulsometerStatisticAnalyzerImpl analyzer, Map<Integer, List<PulsometerDataDto>> timeSegmentedStats, Set<Integer> keys) {
        double[] timeSegKurtosis = keys.stream().mapToDouble(key -> analyzer.getKurtosis(timeSegmentedStats.get(key), PulsometerDataDto::getValue)).toArray();
        double[] timeSeries = DoubleStream.iterate(0, n -> n + getTimeIntervalMillis()).limit(timeSegKurtosis.length).toArray();
        new ChartGeneratorImpl().drawChart(new Plot.Builder(new Object[]{timeSegKurtosis}, timeSeries)
                .withFileName("puls-chart-kurtosis")
                .withGrid()
                .withLegend("{'Kurtoza'}")
                .withTitle("Pulsometr - kurtoza")
                .withXAxisName("Czas")
                .withYAxisName("Wartość")
                .build(MainController.getDestinationSubFolder()));
    }

    private void generateAvgChart(PulsometerStatisticAnalyzerImpl analyzer, Map<Integer, List<PulsometerDataDto>> timeSegmentedStats, Set<Integer> keys) {
        double[] timeSegAvg = keys.stream().mapToDouble(key -> analyzer.getAverage(timeSegmentedStats.get(key), PulsometerDataDto::getValue)).toArray();
        double[] timeSeries = DoubleStream.iterate(0, n -> n + getTimeIntervalMillis()).limit(timeSegAvg.length).toArray();
        new ChartGeneratorImpl().drawChart(new Plot.Builder(new Object[]{timeSegAvg}, timeSeries)
                .withFileName("puls-chart-avg")
                .withGrid()
                .withLegend("{'Wartość'}")
                .withTitle("Pulsometr")
                .withXAxisName("Czas")
                .withYAxisName("Wartość")
                .build(MainController.getDestinationSubFolder()));
    }

    @Override
    public boolean supports(String fileType) {
        return Constants.PULSOMETER.equals(fileType);
    }
}
