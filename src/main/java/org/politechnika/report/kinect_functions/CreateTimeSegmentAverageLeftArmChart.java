package org.politechnika.report.kinect_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.Function;

public class CreateTimeSegmentAverageLeftArmChart implements Function<TimeIntervalPointDistanceStatistics, TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getAverageValue(Sensor.SHOULDER_LEFT),
                                handStatistics.getAverageValue(Sensor.ELBOW_LEFT),
                                handStatistics.getAverageValue(Sensor.HAND_LEFT),
                                handStatistics.getAverageValue(Sensor.THUMB_LEFT),
                                handStatistics.getAverageValue(Sensor.HAND_TIP_LEFT)
                        }, handStatistics.getTimeDimension())
                        .withFileName("left_arm_sensors_averages")
                        .withGrid()
                        .withLegend("{'Ramie', 'Łokieć', 'Dłoń', 'Kciuk', 'Palec wskazujący'}")
                        .withTitle("Średnie wartości lewej ręki")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Średnia")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
