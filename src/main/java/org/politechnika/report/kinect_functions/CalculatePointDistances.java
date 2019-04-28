package org.politechnika.report.kinect_functions;

import org.politechnika.data_parser.model.KinectDataDto;
import org.politechnika.model.kinect.PointDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CalculatePointDistances implements Function<List<KinectDataDto>, List<PointDistance>> {

    private PointVectorLengthCalculator lengthCalculator;

    public CalculatePointDistances() {
        this.lengthCalculator = new PointVectorLengthCalculator();
    }

    @Override
    public List<PointDistance> apply(List<KinectDataDto> kinectDataDtos) {
        List<PointDistance> points = new ArrayList<>();
        for (int i = 1 ; i < kinectDataDtos.size() - 1 ; i++) {
            points.add(calculatePoint(kinectDataDtos.get(i), kinectDataDtos.get(i-1), i));
        }

        return points;
    }

    private PointDistance calculatePoint(KinectDataDto data, KinectDataDto data1, int i) {
        PointDistance pointDistance = new PointDistance("Point " + i);
        lengthCalculator.calculateVectorLength(pointDistance, data1, data);
        pointDistance.setTime(data.getTimestamp());
        return pointDistance;
    }
}
