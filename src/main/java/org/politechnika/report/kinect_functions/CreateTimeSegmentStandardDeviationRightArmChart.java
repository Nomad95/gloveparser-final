package org.politechnika.report.kinect_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.Function;

public class CreateTimeSegmentStandardDeviationRightArmChart implements Function<TimeIntervalPointDistanceStatistics, TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getStandardDeviationValue(Sensor.SHOULDER_RIGHT),
                                handStatistics.getStandardDeviationValue(Sensor.ELBOW_RIGHT),
                                handStatistics.getStandardDeviationValue(Sensor.HAND_RIGHT),
                                handStatistics.getStandardDeviationValue(Sensor.THUMB_RIGHT),
                                handStatistics.getStandardDeviationValue(Sensor.HAND_TIP_RIGHT)
                        }, handStatistics.getTimeDimension())
                        .withFileName("right_arm_sensors_standard_deviation")
                        .withGrid()
                        .withLegend("{'Ramie', 'Łokieć', 'Dłoń', 'Kciuk', 'Palec wskazujący'}")
                        .withTitle("Wartości odchylenia standardowego prawej ręki")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Odchylenie standardowe")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
