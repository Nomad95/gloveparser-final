package org.politechnika.report.impl.kinect_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.Function;

public class CreateTimeSegmentVarianceLeftLegChart implements Function<TimeIntervalPointDistanceStatistics, TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getVarianceValue(Sensor.WRIST_LEFT),
                                handStatistics.getVarianceValue(Sensor.HIP_LEFT),
                                handStatistics.getVarianceValue(Sensor.KNEE_LEFT),
                                handStatistics.getVarianceValue(Sensor.FOOT_LEFT)
                        }, handStatistics.getTimeDimension())
                        .withFileName("left_leg_sensors_variances")
                        .withGrid()
                        .withLegend("{'Talia', 'Biodro', 'Kolano', 'Stopa'}")
                        .withTitle("Wartości wariancji lewej nogi")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Wariancja")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
