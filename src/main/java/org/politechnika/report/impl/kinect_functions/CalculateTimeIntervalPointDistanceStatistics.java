package org.politechnika.report.impl.kinect_functions;

import org.politechnika.analysis.impl.KinectPointDistanceAnalyzerImpl;
import org.politechnika.model.kinect.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CalculateTimeIntervalPointDistanceStatistics implements Function<Map<Long, List<PointDistance>>, TimeIntervalPointDistanceStatistics> {

    private final KinectPointDistanceAnalyzerImpl kinectStatisticAnalyzer;
    TerrifyingPointDistanceSetter terrifyingKinectSetter;

    public CalculateTimeIntervalPointDistanceStatistics() {
        this.kinectStatisticAnalyzer = new KinectPointDistanceAnalyzerImpl();
        this.terrifyingKinectSetter = new TerrifyingPointDistanceSetter();
    }

    @Override
    public TimeIntervalPointDistanceStatistics apply(Map<Long, List<PointDistance>> dataByFingersOfOneHand) {
        TimeIntervalPointDistanceStatistics statsByTimeInterval = new TimeIntervalPointDistanceStatistics();
        calculateStatsForEveryTimeInterval(dataByFingersOfOneHand, statsByTimeInterval);

        return statsByTimeInterval;
    }

    private void calculateStatsForEveryTimeInterval(Map<Long, List<PointDistance>> kinectData, TimeIntervalPointDistanceStatistics statsByTimeInterval) {
        for (List<PointDistance> dataSection : kinectData.values()) {
            PointDistanceStatistics statistics = calculateStatsForTimeInterval(dataSection);
            statsByTimeInterval.addStatstics(statistics);
        }
    }

    @SuppressWarnings("Duplicates")
    private PointDistanceStatistics calculateStatsForTimeInterval(List<PointDistance> kinectData) {
        PointDistanceStatistics statistics = new PointDistanceStatistics();
        terrifyingKinectSetter.setAllAverages(statistics, kinectData);
        terrifyingKinectSetter.setAllVariances(statistics, kinectData);
        terrifyingKinectSetter.setAllStandardDeviations(statistics, kinectData);
        terrifyingKinectSetter.setSkewnessCoefficients(statistics, kinectData);
        terrifyingKinectSetter.setAllKurtosis(statistics, kinectData);

        return statistics;
    }
}
