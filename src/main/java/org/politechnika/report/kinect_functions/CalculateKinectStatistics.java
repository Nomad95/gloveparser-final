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
    public KinectStatistics apply(List<KinectDataDto> kinectData) {
        KinectStatistics kinectStatistics = new KinectStatistics();
        setter.setAllAverages(kinectStatistics, kinectData);
        setter.setAllVariances(kinectStatistics, kinectData);
        setter.setAllStandardDeviations(kinectStatistics, kinectData);
        setter.setSkewnessCoefficients(kinectStatistics, kinectData);
        setter.setAllKurtosis(kinectStatistics, kinectData);

        return kinectStatistics;
    }


}
