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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

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
        double average = analyzer.getAverage(pulsometerDataDtos, PulsometerDataDto::getValue);
        double kurtosis = analyzer.getKurtosis(pulsometerDataDtos, PulsometerDataDto::getValue);
        double skewness = analyzer.getSkewness(pulsometerDataDtos, PulsometerDataDto::getValue);
        double deviation = analyzer.getStandardDeviation(pulsometerDataDtos, PulsometerDataDto::getValue);
        double variance = analyzer.getVariance(pulsometerDataDtos, PulsometerDataDto::getValue);

//        rawDataPartitionedByHand.get(hand).stream()
//                                .collect(groupingBy(datum -> datum.getTimestamp().toEpochMilli() / getTimeIntervalMillis()))
//
        Map<Integer, List<PulsometerDataDto>> timeSegmentedStats =
                pulsometerDataDtos.stream()
                                  .collect(groupingBy(time -> {
                                      return (time.getTime().toSecondOfDay() * 1000) / getTimeIntervalMillis();
                                  }));

        Set<Integer> keys = timeSegmentedStats.keySet();
        double[] timeSegAvg = keys.stream().mapToDouble(key -> analyzer.getAverage(timeSegmentedStats.get(key), PulsometerDataDto::getValue)).toArray();

        double[] timeSeries = DoubleStream.iterate(0, n -> n + getTimeIntervalMillis()).limit(timeSegAvg.length).toArray();
        new ChartGeneratorImpl().drawChart(new Plot.Builder(new Object[]{timeSegAvg}, timeSeries)
                .withFileName("puls-chart")
                .withGrid()
                .withLegend("{'Wartość'}")
                .withTitle("Pulsometr")
                .withXAxisName("Czas")
                .withYAxisName("Wartość")
                .build(MainController.getDestinationSubFolder()));



        //TODO: load data
        // -> process and aggregate
        // -> save to cache if needed for further processing
        // -> save result to file (LAZY!)
        // -> generate and save charts
    }

    @Override
    public boolean supports(String fileType) {
        return Constants.PULSOMETER.equals(fileType);
    }
}
