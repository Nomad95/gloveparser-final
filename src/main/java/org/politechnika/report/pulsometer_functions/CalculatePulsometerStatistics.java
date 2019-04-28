package org.politechnika.report.pulsometer_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.analysis.StandardStatisticsAnalyzerImpl;
import org.politechnika.data_parser.model.PulsometerDataDto;
import org.politechnika.model.pulsometer.PulsometerStatistics;

import java.util.List;
import java.util.function.Function;

@Slf4j
public class CalculatePulsometerStatistics implements Function<List<PulsometerDataDto>, PulsometerStatistics> {

    @Override
    public PulsometerStatistics apply(List<PulsometerDataDto> pulsometerDataDtos) {
        log.debug("Calculating pulsometer statistics");

        StandardStatisticsAnalyzerImpl analyzer = new StandardStatisticsAnalyzerImpl();
        double average = analyzer.getAverage(pulsometerDataDtos, PulsometerDataDto::getValue);
        double kurtosis = analyzer.getKurtosis(pulsometerDataDtos, PulsometerDataDto::getValue);
        double skewness = analyzer.getSkewness(pulsometerDataDtos, PulsometerDataDto::getValue);
        double deviation = analyzer.getStandardDeviation(pulsometerDataDtos, PulsometerDataDto::getValue);
        double variance = analyzer.getVariance(pulsometerDataDtos, PulsometerDataDto::getValue);

        return new PulsometerStatistics(average, deviation, variance, skewness, kurtosis);
    }
}
