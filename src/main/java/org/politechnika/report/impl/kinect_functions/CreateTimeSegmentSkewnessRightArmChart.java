package org.politechnika.report.impl.kinect_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.Function;

public class CreateTimeSegmentSkewnessRightArmChart implements Function<TimeIntervalPointDistanceStatistics, TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getSkewnessValue(Sensor.SHOULDER_RIGHT),
                                handStatistics.getSkewnessValue(Sensor.ELBOW_RIGHT),
                                handStatistics.getSkewnessValue(Sensor.HAND_RIGHT),
                                handStatistics.getSkewnessValue(Sensor.THUMB_RIGHT),
                                handStatistics.getSkewnessValue(Sensor.HAND_TIP_RIGHT)
                        }, handStatistics.getTimeDimension())
                        .withFileName("right_arm_sensors_skewnesses")
                        .withGrid()
                        .withLegend("{'Ramie', 'Łokieć', 'Dłoń', 'Kciuk', 'Palec wskazujący'}")
                        .withTitle("Wartości skośności prawej ręki")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Skośność")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
