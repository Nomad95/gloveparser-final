package org.politechnika.report.kinect_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.Function;

public class CreateTimeSegmentAverageLeftLegChart implements Function<TimeIntervalPointDistanceStatistics, TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getAverageValue(Sensor.WRIST_LEFT),
                                handStatistics.getAverageValue(Sensor.HIP_LEFT),
                                handStatistics.getAverageValue(Sensor.KNEE_LEFT),
                                handStatistics.getAverageValue(Sensor.FOOT_LEFT)
                        }, handStatistics.getTimeDimension())
                        .withFileName("left_leg_sensors_averages")
                        .withGrid()
                        .withLegend("{'Talia', 'Biodro', 'Kolano', 'Stopa'}")
                        .withTitle("Średnie wartości lewej nogi")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Średnia")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
