package org.politechnika.report.glove_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.model.glove.Finger;
import org.politechnika.model.glove.HandStatistics;
import org.politechnika.model.glove.TimeIntervalHandStatistics;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static org.politechnika.report.functions.Functions.sensorToFinger;

@Slf4j
public class CalculateTimeIntervalStatistics implements Function<Map<Long, List<GloveDataDto>>, TimeIntervalHandStatistics> {

    private String handName;

    public CalculateTimeIntervalStatistics(String handName) {
        this.handName = handName;
    }

    @Override
    public TimeIntervalHandStatistics apply(Map<Long, List<GloveDataDto>> handDataPartitionedByTime) {
        log.debug(String.format("Creating time interval statistics for %s hand", handName));
        TimeIntervalHandStatistics statsByTimeInterval = new TimeIntervalHandStatistics();
        calculateStatsForEveryTimeInterval(handDataPartitionedByTime, statsByTimeInterval);
        statsByTimeInterval.setHandName(handName);
        log.debug("Calculation finished");

        return statsByTimeInterval;
    }

    private void calculateStatsForEveryTimeInterval(Map<Long, List<GloveDataDto>> dataByFingersOfOneHand,
                                                    TimeIntervalHandStatistics statsByTimeInterval) {
        for (List<GloveDataDto> dataSection : dataByFingersOfOneHand.values()) {
            Map<Finger, List<GloveDataDto>> dataSectionByFingers = partitionDataSectionByFinger(dataSection);
            HandStatistics statistics = calculateStatistics(dataSectionByFingers);
            statsByTimeInterval.addStatstics(statistics);
        }
    }

    private Map<Finger, List<GloveDataDto>> partitionDataSectionByFinger(List<GloveDataDto> dataSection) {
        return dataSection.stream()
                .collect(groupingBy(sensorToFinger()));
    }

    private HandStatistics calculateStatistics(Map<Finger, List<GloveDataDto>> dataSectionByFingers) {
        CalculateGloveStatistics gloveStatistics = new CalculateGloveStatistics(handName);
        return gloveStatistics.apply(dataSectionByFingers);
    }
}
