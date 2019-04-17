package org.politechnika.report.impl.kinect_functions;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.data_parser.model.KinectDataDto;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.model.kinect.KinectStatistics;
import org.politechnika.model.kinect.PointDistanceStatistics;
import org.politechnika.model.kinect.TimeIntervalKinectStatistics;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Builder
public class GenerateKinectReport {

    private AbstractDataFile fromFile;

    private Function<AbstractDataFile, List<KinectDataDto>> parseData;
    private Function<List<KinectDataDto>, KinectStatistics> doCalculations;
    private Function<Map<Long, List<KinectDataDto>>, TimeIntervalKinectStatistics> doCalculationsWithTimeInterval;

    private Function<List<KinectDataDto>, PointDistanceStatistics> doPointsCalculations;
    private Function<Map<Long, List<KinectDataDto>>, TimeIntervalPointDistanceStatistics> doPointsCalculationsWithTimeInterval;

    public void generate() {

        List<KinectDataDto> rawData = parseData.apply(fromFile);
        doCalculations.apply(rawData);

        log.debug("Kinect time intervals");
        Map<Long, List<KinectDataDto>> kinectDataPartitionedByTimeInterval = rawData.stream()
                .collect(groupingBy(datum -> datum.getTimestamp().toEpochMilli() / MainController.getTimeIntervalMillis()));
        doCalculationsWithTimeInterval.apply(kinectDataPartitionedByTimeInterval);

        doPointsCalculations.apply(rawData);
        doPointsCalculationsWithTimeInterval.apply(kinectDataPartitionedByTimeInterval);

        //TODO add to correlation report
        //TODO add to inference report
    }
}
