package org.politechnika.report.impl.glove_functions;

import org.politechnika.analysis.impl.GloveStatisticsAnalyzerImpl;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;
import org.politechnika.model.Finger;
import org.politechnika.model.HandStatistics;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CalculateGloveStatistics implements Function<Map<Finger, List<GloveDataDto>>, HandStatistics> {

    private String handName;
    private final GloveStatisticsAnalyzerImpl statisticsAnalyzer;

    public CalculateGloveStatistics(String handName) {
        this.statisticsAnalyzer = new GloveStatisticsAnalyzerImpl();
        this.handName = handName;
    }

    @Override
    public HandStatistics apply(Map<Finger, List<GloveDataDto>> dataByFingersOfOneHand) {
        HandStatistics handStatistics = new HandStatistics(handName);
        for (Map.Entry<Finger, List<GloveDataDto>> dataByFinger : dataByFingersOfOneHand.entrySet()) {
            handStatistics.setAverageFor(dataByFinger.getKey(), statisticsAnalyzer.getAverage(dataByFinger.getValue(), GloveDataDto::getRaw));
            handStatistics.setVarianceFor(dataByFinger.getKey(), statisticsAnalyzer.getVariance(dataByFinger.getValue(), GloveDataDto::getRaw));
            handStatistics.setStandardDeviationFor(dataByFinger.getKey(), statisticsAnalyzer.getStandardDeviation(dataByFinger.getValue(), GloveDataDto::getRaw));
            handStatistics.setSkewnessCoefficientFor(dataByFinger.getKey(), statisticsAnalyzer.getSkewness(dataByFinger.getValue(), GloveDataDto::getRaw));
            handStatistics.setKurtosisFor(dataByFinger.getKey(), statisticsAnalyzer.getKurtosis(dataByFinger.getValue(), GloveDataDto::getRaw));
        }

        return handStatistics;
    }

    public void setHandName(String handName) {
        this.handName = handName;
    }
}
