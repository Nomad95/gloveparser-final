package org.politechnika.report.pulsometer_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.analysis.StandardStatisticsAnalyzerImpl;
import org.politechnika.data_parser.model.PulsometerDataDto;

import java.util.List;
import java.util.function.UnaryOperator;

@Slf4j
public class CalculatePulsometerStatistics implements UnaryOperator<List<PulsometerDataDto>> {

    @Override
    public List<PulsometerDataDto> apply(List<PulsometerDataDto> pulsometerDataDtos) {
        log.debug("Calculating pulsometer statistics");

        StandardStatisticsAnalyzerImpl analyzer = new StandardStatisticsAnalyzerImpl();
        double average = analyzer.getAverage(pulsometerDataDtos, PulsometerDataDto::getValue);//todo: te wartosci pozniej do raportu koncowego
        double kurtosis = analyzer.getKurtosis(pulsometerDataDtos, PulsometerDataDto::getValue);
        double skewness = analyzer.getSkewness(pulsometerDataDtos, PulsometerDataDto::getValue);
        double deviation = analyzer.getStandardDeviation(pulsometerDataDtos, PulsometerDataDto::getValue);
        double variance = analyzer.getVariance(pulsometerDataDtos, PulsometerDataDto::getValue);

        return pulsometerDataDtos;
    }
}
