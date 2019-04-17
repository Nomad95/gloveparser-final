package org.politechnika.report.impl.kinect_functions;

import org.politechnika.analysis.StandardStatisticsAnalyzerImpl;
import org.politechnika.data_parser.csv.definitions.beans.KinectDataDto;
import org.politechnika.model.kinect.KinectStatistics;
import org.politechnika.model.kinect.TimeIntervalKinectStatistics;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CalculateTimeIntervalKinectStatistics implements Function<Map<Long, List<KinectDataDto>>, TimeIntervalKinectStatistics> {

    private final StandardStatisticsAnalyzerImpl kinectStatisticAnalyzer;
    TerrifyingKinectSetter terrifyingKinectSetter;

    public CalculateTimeIntervalKinectStatistics() {
        this.kinectStatisticAnalyzer = new StandardStatisticsAnalyzerImpl();
        this.terrifyingKinectSetter = new TerrifyingKinectSetter();
    }

    @Override
    public TimeIntervalKinectStatistics apply(Map<Long, List<KinectDataDto>> dataByFingersOfOneHand) {
        TimeIntervalKinectStatistics statsByTimeInterval = new TimeIntervalKinectStatistics();
        calculateStatsForEveryTimeInterval(dataByFingersOfOneHand, statsByTimeInterval);

        return statsByTimeInterval;
    }

    private void calculateStatsForEveryTimeInterval(Map<Long, List<KinectDataDto>> kinectData, TimeIntervalKinectStatistics statsByTimeInterval) {
        for (List<KinectDataDto> dataSection : kinectData.values()) {
            KinectStatistics statistics = calculateStatsForTimeInterval(dataSection);
            statsByTimeInterval.addStatstics(statistics);
        }
    }

    @SuppressWarnings("Duplicates")
    private KinectStatistics calculateStatsForTimeInterval(List<KinectDataDto> kinectData) {
        KinectStatistics statistics = new KinectStatistics();
        terrifyingKinectSetter.setAllAverages(statistics, kinectData);
        terrifyingKinectSetter.setAllVariances(statistics, kinectData);
        terrifyingKinectSetter.setAllStandardDeviations(statistics, kinectData);
        terrifyingKinectSetter.setSkewnessCoefficients(statistics, kinectData);
        terrifyingKinectSetter.setAllKurtosis(statistics, kinectData);

        return statistics;
    }
}
