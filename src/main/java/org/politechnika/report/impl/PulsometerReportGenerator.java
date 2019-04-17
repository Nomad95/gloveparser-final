package org.politechnika.report.impl;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.analysis.StandardStatisticsAnalyzerImpl;
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
import java.util.TreeMap;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Predicate;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Slf4j
public class PulsometerReportGenerator implements ReportGenerator {

    private static final Predicate<ValueAndTimeSeries> EMPTY_VALUES = entry -> entry.getValue() != 0.0;

    @Override
    public void generate(AbstractDataFile dataFile) {
        log.debug("Generating pulsometer report");

        BeanCsvParser beanCsvParser = new BeanCsvParser();
        List<PulsometerDataDto> pulsometerDataDtos;
        try {
            log.debug("Parsing pulsometer data...");
            pulsometerDataDtos = beanCsvParser.parseToBean(dataFile, new PulsometerParsingStrategy());
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }

        log.debug("Calculating pulsometer statistics");
        StandardStatisticsAnalyzerImpl analyzer = new StandardStatisticsAnalyzerImpl();
        double average = analyzer.getAverage(pulsometerDataDtos, PulsometerDataDto::getValue);//todo: te wartosci pozniej do raportu koncowego
        double kurtosis = analyzer.getKurtosis(pulsometerDataDtos, PulsometerDataDto::getValue);
        double skewness = analyzer.getSkewness(pulsometerDataDtos, PulsometerDataDto::getValue);
        double deviation = analyzer.getStandardDeviation(pulsometerDataDtos, PulsometerDataDto::getValue);
        double variance = analyzer.getVariance(pulsometerDataDtos, PulsometerDataDto::getValue);

        Map<Integer, List<PulsometerDataDto>> timeSegmentedStats =
                pulsometerDataDtos.stream()
                        .collect(groupingBy(time -> time.getTime().toSecondOfDay(), TreeMap::new, toList()));

        generatePulsChart(analyzer, timeSegmentedStats);//todo: reszta wykresow jest bez sensu jeśli nie dzielimy pulsu po czasie
        log.debug("Pulsometer report was generated");
    }

    //todo: wydzielić to do klas i metod czy dzieś
    private void generatePulsChart(StandardStatisticsAnalyzerImpl analyzer, Map<Integer, List<PulsometerDataDto>> timeSegmentedStats) {
        log.debug("Generating pulsometer chart");
        List<ValueAndTimeSeries> timeSegmentedData = timeSegmentedStats.entrySet().stream()
                .map(entry -> new ValueAndTimeSeries(analyzer.getAverage(entry.getValue(), PulsometerDataDto::getValue), entry.getKey()))
                .filter(EMPTY_VALUES)
                .collect(toList());
        if (!timeSegmentedData.isEmpty()) {
            double firstSecondValue = timeSegmentedData.get(0).getTime();
            double[] valueSeries = timeSegmentedData.stream()
                    .mapToDouble(ValueAndTimeSeries::getValue)
                    .toArray();
            double[] timeSeries = timeSegmentedData.stream()
                    .mapToDouble(ValueAndTimeSeries::getTime)
                    .map(timeStartFromZero(firstSecondValue))
                    .toArray();
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{valueSeries}, timeSeries)
                            .withFileName("puls-chart")
                            .withGrid()
                            .withTitle("Pulsometr - Dynamika pulsu")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Wartość")
                            .build(MainController.getDestinationSubFolder()));
        }
    }

    private DoubleUnaryOperator timeStartFromZero(double firstValue) {
        return v -> v - firstValue;
    }

    @Override
    public boolean supports(String fileType) {
        return Constants.PULSOMETER.equals(fileType);
    }

    @Value
    private static class ValueAndTimeSeries {
        private double value;
        private double time;
    }
}
