package org.politechnika.report.kinect_functions;

import org.politechnika.data_parser.model.KinectDataDto;
import org.politechnika.model.kinect.PointDistanceStatistics;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CalculateTimeIntervalPointDistanceStatistics implements Function<Map<Long, List<KinectDataDto>>, TimeIntervalPointDistanceStatistics> {

    private TerrifyingPointDistanceSetter setter;

    public CalculateTimeIntervalPointDistanceStatistics() {
        this.setter = new TerrifyingPointDistanceSetter();
    }

    @Override
    public TimeIntervalPointDistanceStatistics apply(Map<Long, List<KinectDataDto>> kinectDataPartitionedByTimeInterval) {

        TimeIntervalPointDistanceStatistics statsByTimeInterval = new TimeIntervalPointDistanceStatistics();
        calculateStatsForEveryTimeInterval(kinectDataPartitionedByTimeInterval, statsByTimeInterval);

        return statsByTimeInterval;
    }

    private void calculateStatsForEveryTimeInterval(Map<Long, List<KinectDataDto>> kinectDataPartitionedByTimeInterval, TimeIntervalPointDistanceStatistics statsByTimeInterval) {
        for (List<KinectDataDto> dataSection : kinectDataPartitionedByTimeInterval.values()) {
            PointDistanceStatistics statistics = new CalculatePointDistanceStatistics().apply(dataSection);
            statsByTimeInterval.addStatstics(statistics);
        }
    }
}
