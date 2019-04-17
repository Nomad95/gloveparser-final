package org.politechnika.report.impl.kinect_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.Function;

public class CreateTimeSegmentAverageRightArmChart implements Function<TimeIntervalPointDistanceStatistics, TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getAverageValue(Sensor.SHOULDER_RIGHT),
                                handStatistics.getAverageValue(Sensor.ELBOW_RIGHT),
                                handStatistics.getAverageValue(Sensor.HAND_RIGHT),
                                handStatistics.getAverageValue(Sensor.THUMB_RIGHT),
                                handStatistics.getAverageValue(Sensor.HAND_TIP_RIGHT)
                        }, handStatistics.getTimeDimension())
                        .withFileName("right_arm_sensors_averages")
                        .withGrid()
                        .withLegend("{'Ramie', 'Łokieć', 'Dłoń', 'Kciuk', 'Palec wskazujący'}")
                        .withTitle("Średnie wartości prawej ręki")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Średnia")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
