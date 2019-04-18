package org.politechnika.report.kinect_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.Function;

public class CreateTimeSegmentStandardDeviationRightLegChart implements Function<TimeIntervalPointDistanceStatistics, TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getStandardDeviationValue(Sensor.WRIST_RIGHT),
                                handStatistics.getStandardDeviationValue(Sensor.HIP_RIGHT),
                                handStatistics.getStandardDeviationValue(Sensor.KNEE_RIGHT),
                                handStatistics.getStandardDeviationValue(Sensor.FOOT_RIGHT)
                        }, handStatistics.getTimeDimension())
                        .withFileName("right_leg_sensors_standard_deviation")
                        .withGrid()
                        .withLegend("{'Talia', 'Biodro', 'Kolano', 'Stopa'}")
                        .withTitle("Wartości odchylenia standardowego prawej nogi")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Odchylenie standardowe")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
