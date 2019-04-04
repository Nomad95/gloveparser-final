package org.politechnika.report.impl.kinect_functions;

import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.Function;

public class CreateTimeSegmentAverageHeadChart implements Function<TimeIntervalPointDistanceStatistics, TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getAverageValue(Sensor.HEAD)
                        }, handStatistics.getTimeDimension())
                        .withFileName("head_hand_average")
                        .withGrid()
                        .withLegend("{'Głowa'}")
                        .withTitle("Średnie wartości")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Średnia")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
