package org.politechnika.report.kinect_functions;

import org.politechnika.data_parser.model.KinectDataDto;
import org.politechnika.model.kinect.KinectStatistics;

import java.util.List;
import java.util.function.Function;

public class CalculateKinectStatistics implements Function<List<KinectDataDto>, KinectStatistics> {

    private TerrifyingKinectSetter setter;

    public CalculateKinectStatistics() {
        this.setter = new TerrifyingKinectSetter();
    }

    @Override
    @SuppressWarnings("Duplicates")
    public KinectStatistics apply(List<KinectDataDto> data) {
        KinectStatistics kinectStatistics = new KinectStatistics();
        setter.setAllAverages(kinectStatistics, data);
        setter.setAllVariances(kinectStatistics, data);
        setter.setAllStandardDeviations(kinectStatistics, data);
        setter.setSkewnessCoefficients(kinectStatistics, data);
        setter.setAllKurtosis(kinectStatistics, data);

        return kinectStatistics;
    }


}
