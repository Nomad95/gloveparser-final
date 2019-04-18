package org.politechnika.report.kinect_functions;

import org.politechnika.data_parser.model.KinectDataDto;
import org.politechnika.model.kinect.KinectStatistics;
import org.politechnika.model.kinect.TimeIntervalKinectStatistics;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CalculateTimeIntervalKinectStatistics implements Function<Map<Long, List<KinectDataDto>>, TimeIntervalKinectStatistics> {

    @Override
    public TimeIntervalKinectStatistics apply(Map<Long, List<KinectDataDto>> kinectDataPartitionedByTimeInterval) {
        TimeIntervalKinectStatistics statsByTimeInterval = new TimeIntervalKinectStatistics();
        calculateStatsForEveryTimeInterval(kinectDataPartitionedByTimeInterval, statsByTimeInterval);

        return statsByTimeInterval;
    }

    private void calculateStatsForEveryTimeInterval(Map<Long, List<KinectDataDto>> kinectDataPartitionedByTimeInterval, TimeIntervalKinectStatistics statsByTimeInterval) {
        for (List<KinectDataDto> dataSection : kinectDataPartitionedByTimeInterval.values()) {
            KinectStatistics statistics = new CalculateKinectStatistics().apply(dataSection);
            statsByTimeInterval.addStatstics(statistics);
        }
    }
}
