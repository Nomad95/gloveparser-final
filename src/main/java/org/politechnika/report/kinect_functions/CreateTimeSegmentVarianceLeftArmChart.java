package org.politechnika.report.kinect_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.Function;

public class CreateTimeSegmentVarianceLeftArmChart implements Function<TimeIntervalPointDistanceStatistics, TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getVarianceValue(Sensor.SHOULDER_LEFT),
                                handStatistics.getVarianceValue(Sensor.ELBOW_LEFT),
                                handStatistics.getVarianceValue(Sensor.HAND_LEFT),
                                handStatistics.getVarianceValue(Sensor.THUMB_LEFT),
                                handStatistics.getVarianceValue(Sensor.HAND_TIP_LEFT)
                        }, handStatistics.getTimeDimension())
                        .withFileName("left_arm_sensors_variances")
                        .withGrid()
                        .withLegend("{'Ramie', 'Łokieć', 'Dłoń', 'Kciuk', 'Palec wskazujący'}")
                        .withTitle("Wartości wariancji lewej ręki")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Wariancja")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
