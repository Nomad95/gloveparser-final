package org.politechnika.report.impl.glove_functions;

import org.politechnika.analysis.impl.GloveStatisticsAnalyzerImpl;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;
import org.politechnika.model.Finger;
import org.politechnika.model.HandStatistics;
import org.politechnika.model.TimeIntervalHandStatistics;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static org.politechnika.report.functions.GloveFunctions.sensorToFinger;

public class CalculateTimeIntervalStatistics implements Function<Map<Long, List<GloveDataDto>>, TimeIntervalHandStatistics> {

    private String handName = "";
    private final GloveStatisticsAnalyzerImpl statisticsAnalyzer;

    public CalculateTimeIntervalStatistics() {
        this.statisticsAnalyzer = new GloveStatisticsAnalyzerImpl();
    }

    @Override
    public TimeIntervalHandStatistics apply(Map<Long, List<GloveDataDto>> dataByFingersOfOneHand) {
        TimeIntervalHandStatistics statsByTimeInterval = new TimeIntervalHandStatistics();
        calculateStatsForEveryTimeInterval(dataByFingersOfOneHand, statsByTimeInterval);

        return statsByTimeInterval;
    }

    private void calculateStatsForEveryTimeInterval(Map<Long, List<GloveDataDto>> dataByFingersOfOneHand, TimeIntervalHandStatistics statsByTimeInterval) {
        for (List<GloveDataDto> dataSection : dataByFingersOfOneHand.values()) {
            Map<Finger, List<GloveDataDto>> dataSectionByFingers = partitionDataSectionByFinger(dataSection);
            HandStatistics statistics = calculateStatsForTimeInterval(dataSectionByFingers);
            statsByTimeInterval.addStatstics(statistics);
        }
    }

    private Map<Finger, List<GloveDataDto>> partitionDataSectionByFinger(List<GloveDataDto> dataSection) {
        return dataSection.stream()
                .collect(groupingBy(sensorToFinger()));
    }

    private HandStatistics calculateStatsForTimeInterval(Map<Finger, List<GloveDataDto>> dataSectionByFingers) {
        HandStatistics statistics = new HandStatistics(handName);
        for (Map.Entry<Finger, List<GloveDataDto>> dataByFinger : dataSectionByFingers.entrySet()) {
            statistics.setAverageFor(dataByFinger.getKey(), statisticsAnalyzer.getAverage(dataByFinger.getValue(), GloveDataDto::getRaw));
            statistics.setVarianceFor(dataByFinger.getKey(), statisticsAnalyzer.getVariance(dataByFinger.getValue(), GloveDataDto::getRaw));
            statistics.setStandardDeviationFor(dataByFinger.getKey(), statisticsAnalyzer.getStandardDeviation(dataByFinger.getValue(), GloveDataDto::getRaw));
            statistics.setSkewnessCoefficientFor(dataByFinger.getKey(), statisticsAnalyzer.getSkewness(dataByFinger.getValue(), GloveDataDto::getRaw));
            statistics.setKurtosisFor(dataByFinger.getKey(), statisticsAnalyzer.getKurtosis(dataByFinger.getValue(), GloveDataDto::getRaw));
        }

        return statistics;
    }
}
