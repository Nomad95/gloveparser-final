package org.politechnika.report.kinect_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.UnaryOperator;

public class CreateTimeSegmentSkewnessLeftLegChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getSkewnessValue(Sensor.WRIST_LEFT),
                                handStatistics.getSkewnessValue(Sensor.HIP_LEFT),
                                handStatistics.getSkewnessValue(Sensor.KNEE_LEFT),
                                handStatistics.getSkewnessValue(Sensor.FOOT_LEFT)
                        }, handStatistics.getTimeDimension())
                        .withFileName("left_leg_sensors_skewnesses")
                        .withGrid()
                        .withLegend("{'Talia', 'Biodro', 'Kolano', 'Stopa'}")
                        .withTitle("Wartości skośności lewej nogi")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Skośność")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
