package org.politechnika.report.impl.glove_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.analysis.StandardStatisticsAnalyzerImpl;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.model.glove.Finger;
import org.politechnika.model.glove.HandStatistics;
import org.politechnika.model.glove.TimeIntervalHandStatistics;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static org.politechnika.report.functions.GloveFunctions.sensorToFinger;

@Slf4j
public class CalculateTimeIntervalStatistics implements Function<Map<Long, List<GloveDataDto>>, TimeIntervalHandStatistics> {

    private String handName;
    private final StandardStatisticsAnalyzerImpl statisticsAnalyzer;

    public CalculateTimeIntervalStatistics(String handName) {
        this.statisticsAnalyzer = new StandardStatisticsAnalyzerImpl();
        this.handName = handName;
    }

    @Override
    public TimeIntervalHandStatistics apply(Map<Long, List<GloveDataDto>> dataByFingersOfOneHand) {
        log.debug(String.format("Creating time interval statistics for %s hand", handName));
        TimeIntervalHandStatistics statsByTimeInterval = new TimeIntervalHandStatistics();
        calculateStatsForEveryTimeInterval(dataByFingersOfOneHand, statsByTimeInterval);
        statsByTimeInterval.setHandName(handName);
        log.debug("Calculation finished");

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

    @SuppressWarnings("Duplicates")
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
