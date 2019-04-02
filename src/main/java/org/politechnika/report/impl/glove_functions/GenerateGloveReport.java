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
import static org.politechnika.commons.Constants.LEFT_HAND;
import static org.politechnika.commons.Constants.RIGHT_HAND;
import static org.politechnika.frontend.main_controller.MainController.getTimeIntervalMillis;
import static org.politechnika.report.functions.GloveFunctions.sensorToFinger;

@Slf4j
@Builder
public class GenerateGloveReport {

    private AbstractDataFile fromFile;

    private Function<AbstractDataFile, List<GloveDataDto>> parseData;
    private Function<List<GloveDataDto>, Map<String, List<GloveDataDto>>> partitionRawData;
    private Function<Map<String, List<GloveDataDto>>, Map<String, List<GloveDataDto>>> doOnRawData;
    private Function<Map<Finger, List<GloveDataDto>>, HandStatistics> doOnLeftHand;
    private Function<Map<Finger, List<GloveDataDto>>, HandStatistics> doOnRightHand;
    private Function<Map<Long, List<GloveDataDto>>, TimeIntervalHandStatistics> doOnLeftHandWithTimeInterval;
    private Function<Map<Long, List<GloveDataDto>>, TimeIntervalHandStatistics> doOnRightHandWithTimeInterval;

    public void generate() {
        log.debug("Parsing data");
        List<GloveDataDto> rawData = parseData.apply(fromFile);

        log.debug("Partitioning data");
        Map<String, List<GloveDataDto>> rawDataPartitionedByHand = partitionRawData.apply(rawData);

        log.debug("Operating on left hand fingers");
        doOnLeftHand.apply(partitionHandByFinger(rawDataPartitionedByHand, LEFT_HAND));

        log.debug("Operating on right hand fingers");
        doOnRightHand.apply(partitionHandByFinger(rawDataPartitionedByHand, RIGHT_HAND));

        log.debug("Operating on left hand time intervals");
        doOnLeftHandWithTimeInterval.apply(partitionHandWithTimeSeries(rawDataPartitionedByHand, LEFT_HAND));

        log.debug("Operating on right hand time intervals");
        doOnRightHandWithTimeInterval.apply(partitionHandWithTimeSeries(rawDataPartitionedByHand, RIGHT_HAND));

        //TODO add to correlation report
        //TODO add to inference report
    }

    private Map<Long, List<GloveDataDto>> partitionHandWithTimeSeries(Map<String, List<GloveDataDto>> rawDataPartitionedByHand, String hand) {
        return rawDataPartitionedByHand.get(hand).stream()
                .collect(groupingBy(datum -> datum.getTimestamp().toEpochMilli() / getTimeIntervalMillis()));
    }

    private Map<Finger, List<GloveDataDto>> partitionHandByFinger(Map<String, List<GloveDataDto>> rawDataPartitionedByHand, String hand) {
        return rawDataPartitionedByHand.get(hand).stream()
                .collect(groupingBy(sensorToFinger()));
    }
}
