package org.politechnika.report.impl.glove_functions;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.model.Finger;
import org.politechnika.model.HandStatistics;
import org.politechnika.model.TimeIntervalHandStatistics;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static org.politechnika.report.functions.GloveFunctions.sensorToFinger;

@Slf4j
@Builder
public class GenerateGloveReport {

    private AbstractDataFile fromFile;
    private static final int TEMP_INTERVAL = 1000;

    private Function<AbstractDataFile, List<GloveDataDto>> parseData;
    private Function<List<GloveDataDto>, Map<String, List<GloveDataDto>>> partitionRawData;
    private Function<Map<Finger, List<GloveDataDto>>, HandStatistics> doOnOneHand;
    private Function<Map<Long, List<GloveDataDto>>, TimeIntervalHandStatistics> doOnOneHandWithTimeInterval;

    public void generate() {

        List<GloveDataDto> rawData = parseData.apply(fromFile);
        Map<String, List<GloveDataDto>> rawDataPartitionedByHand = partitionRawData.apply(rawData);
        log.debug("Left hand");
        Map<Finger, List<GloveDataDto>> dataByFingersOfLeftHand = rawDataPartitionedByHand.get("left").stream()
                .collect(groupingBy(sensorToFinger()));
        doOnOneHand.apply(dataByFingersOfLeftHand);

        log.debug("Right hand");
        Map<Finger, List<GloveDataDto>> dataByFingersOfRightHand = rawDataPartitionedByHand.get("right").stream()
                .collect(groupingBy(sensorToFinger()));
        doOnOneHand.apply(dataByFingersOfRightHand);

        log.debug("Left hand time intervals");
        Map<Long, List<GloveDataDto>> leftHandDataPartitionedByTimeInterval = rawDataPartitionedByHand.get("left").stream()
                .collect(groupingBy(datum -> datum.getTimestamp().toEpochMilli() / TEMP_INTERVAL));
        doOnOneHandWithTimeInterval.apply(leftHandDataPartitionedByTimeInterval);

        log.debug("Right hand time intervals");
        Map<Long, List<GloveDataDto>> rightHandDataPartitionedByTimeInterval = rawDataPartitionedByHand.get("right").stream()
                .collect(groupingBy(datum -> datum.getTimestamp().toEpochMilli() / TEMP_INTERVAL));
        doOnOneHandWithTimeInterval.apply(rightHandDataPartitionedByTimeInterval);
        //TODO add to correlation report
        //TODO add to inference report
    }
}
