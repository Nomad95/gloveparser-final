package org.politechnika.report.kinect_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.Function;

public class CreateTimeSegmentSkewnessRightLegChart implements Function<TimeIntervalPointDistanceStatistics, TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getSkewnessValue(Sensor.WRIST_RIGHT),
                                handStatistics.getSkewnessValue(Sensor.HIP_RIGHT),
                                handStatistics.getSkewnessValue(Sensor.KNEE_RIGHT),
                                handStatistics.getSkewnessValue(Sensor.FOOT_RIGHT)
                        }, handStatistics.getTimeDimension())
                        .withFileName("right_leg_sensors_skewnesses")
                        .withGrid()
                        .withLegend("{'Talia', 'Biodro', 'Kolano', 'Stopa'}")
                        .withTitle("Wartości skośności prawej nogi")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Skośność")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
