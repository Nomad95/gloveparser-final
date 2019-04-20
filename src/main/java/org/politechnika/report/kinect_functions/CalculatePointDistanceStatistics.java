package org.politechnika.report.kinect_functions;

import org.politechnika.model.kinect.PointDistance;
import org.politechnika.model.kinect.PointDistanceStatistics;

import java.util.List;
import java.util.function.Function;

public class CalculatePointDistanceStatistics implements Function<List<PointDistance>, PointDistanceStatistics> {

    private TerrifyingPointDistanceSetter setter;

    public CalculatePointDistanceStatistics() {
        this.setter = new TerrifyingPointDistanceSetter();
    }

    @Override
    public PointDistanceStatistics apply(List<PointDistance> points) {

        PointDistanceStatistics kinectStatistics = new PointDistanceStatistics();
        setter.setAllAverages(kinectStatistics, points);
        setter.setAllVariances(kinectStatistics, points);
        setter.setAllStandardDeviations(kinectStatistics, points);
        setter.setSkewnessCoefficients(kinectStatistics, points);
        setter.setAllKurtosis(kinectStatistics, points);

        return kinectStatistics;
    }


}
