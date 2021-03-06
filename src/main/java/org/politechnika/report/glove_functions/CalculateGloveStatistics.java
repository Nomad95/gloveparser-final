package org.politechnika.report.glove_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.analysis.StandardStatisticsAnalyzerImpl;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.model.glove.Finger;
import org.politechnika.model.glove.HandStatistics;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.lang.String.format;

@Slf4j
public class CalculateGloveStatistics implements Function<Map<Finger, List<GloveDataDto>>, HandStatistics> {

    private String handName;
    private final StandardStatisticsAnalyzerImpl statisticsAnalyzer;

    public CalculateGloveStatistics(String handName) {
        this.statisticsAnalyzer = new StandardStatisticsAnalyzerImpl();
        this.handName = handName;
    }

    @Override
    public HandStatistics apply(Map<Finger, List<GloveDataDto>> dataByFingersOfOneHand) {
        log.debug(format("Calculating statistics for %s hand", handName));
        HandStatistics handStatistics = new HandStatistics(handName);
        for (Map.Entry<Finger, List<GloveDataDto>> dataByFinger : dataByFingersOfOneHand.entrySet()) {
            handStatistics.setAverageFor(dataByFinger.getKey(), statisticsAnalyzer.getAverage(dataByFinger.getValue(), GloveDataDto::getRaw));
            handStatistics.setVarianceFor(dataByFinger.getKey(), statisticsAnalyzer.getVariance(dataByFinger.getValue(), GloveDataDto::getRaw));
            handStatistics.setStandardDeviationFor(dataByFinger.getKey(), statisticsAnalyzer.getStandardDeviation(dataByFinger.getValue(), GloveDataDto::getRaw));
            handStatistics.setSkewnessCoefficientFor(dataByFinger.getKey(), statisticsAnalyzer.getSkewness(dataByFinger.getValue(), GloveDataDto::getRaw));
            handStatistics.setKurtosisFor(dataByFinger.getKey(), statisticsAnalyzer.getKurtosis(dataByFinger.getValue(), GloveDataDto::getRaw));
        }
        log.debug("Calculation finished");
        return handStatistics;
    }

}
