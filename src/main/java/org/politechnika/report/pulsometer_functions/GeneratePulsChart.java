package org.politechnika.report.pulsometer_functions;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.analysis.StandardStatisticsAnalyzerImpl;
import org.politechnika.commons.ParserMatlabException;
import org.politechnika.data_parser.model.PulsometerDataDto;
import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Slf4j
public class GeneratePulsChart implements UnaryOperator<List<PulsometerDataDto>> {

    private static final Predicate<ValueAndTimeSeries> EMPTY_VALUES = entry -> entry.getValue() != 0.0;

    @Override
    public List<PulsometerDataDto> apply(List<PulsometerDataDto> pulsometerDataDtos) {
        Map<Integer, List<PulsometerDataDto>> timeSegmentedStats =
                pulsometerDataDtos.stream()
                        .collect(groupingBy(time -> time.getTime().toSecondOfDay(), TreeMap::new, toList()));

        generatePulsChart(timeSegmentedStats);

        return pulsometerDataDtos;
    }

    private void generatePulsChart(Map<Integer, List<PulsometerDataDto>> timeSegmentedStats) {
        log.debug("Generating pulsometer chart");
        StandardStatisticsAnalyzerImpl analyzer = new StandardStatisticsAnalyzerImpl();
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
            drawChart(valueSeries, timeSeries);
        }
    }

    private void drawChart(double[] valueSeries, double[] timeSeries) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{valueSeries}, timeSeries)
                            .withFileName("puls-chart")
                            .withGrid()
                            .withTitle("Pulsometr - Dynamika pulsu")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Wartość")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create Pulsometer chart");
        }
    }

    private DoubleUnaryOperator timeStartFromZero(double firstValue) {
        return v -> v - firstValue;
    }

    @Value
    private static class ValueAndTimeSeries {
        private double value;
        private double time;
    }
}
