package org.politechnika.report.kinect_functions;

import org.politechnika.data_parser.model.KinectDataDto;
import org.politechnika.model.kinect.PointDistance;
import org.politechnika.model.kinect.PointDistanceStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CalculatePointDistanceStatistics implements Function<List<KinectDataDto>, PointDistanceStatistics> {

    private TerrifyingPointDistanceSetter setter;
    private PointVectorLengthCalculator lengthCalculator;

    public CalculatePointDistanceStatistics() {
        this.setter = new TerrifyingPointDistanceSetter();
        this.lengthCalculator = new PointVectorLengthCalculator();
    }

    @Override
    public PointDistanceStatistics apply(List<KinectDataDto> kinectData) {
        List<PointDistance> points = new ArrayList<>();
        for (int i = 1 ; i < kinectData.size() - 1 ; i++) {
            points.add(calculatePoint(kinectData.get(i), kinectData.get(i-1), i));
        }

        PointDistanceStatistics kinectStatistics = new PointDistanceStatistics();
        setter.setAllAverages(kinectStatistics, points);
        setter.setAllVariances(kinectStatistics, points);
        setter.setAllStandardDeviations(kinectStatistics, points);
        setter.setSkewnessCoefficients(kinectStatistics, points);
        setter.setAllKurtosis(kinectStatistics, points);

        return kinectStatistics;
    }

    private PointDistance calculatePoint(KinectDataDto data, KinectDataDto data1, int i) {
        PointDistance pointDistance = new PointDistance("Point " + i);
        lengthCalculator.calculateVectorLength(pointDistance, data1, data);
        return pointDistance;
    }

}
