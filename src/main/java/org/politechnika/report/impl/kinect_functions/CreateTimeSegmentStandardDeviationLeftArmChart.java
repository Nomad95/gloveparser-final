package org.politechnika.report.impl.kinect_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.Function;

public class CreateTimeSegmentStandardDeviationLeftArmChart implements Function<TimeIntervalPointDistanceStatistics, TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getStandardDeviationValue(Sensor.HEAD),
                                handStatistics.getStandardDeviationValue(Sensor.NECK),
                                handStatistics.getStandardDeviationValue(Sensor.SPINE_SHOULDER),
                                handStatistics.getStandardDeviationValue(Sensor.SPINE_MID),
                                handStatistics.getStandardDeviationValue(Sensor.SPINE_BASE)
                        }, handStatistics.getTimeDimension())
                        .withFileName("core_sensors_standard_deviation")
                        .withGrid()
                        .withLegend("{'Głowa', 'Szyja', 'Odcinek szyjny kręgosłupa', 'Odcinek  środkowy kręgosłupa', 'Odcinek lędźwiowy kręgosłupa'}")
                        .withTitle("Wartości odchylenia standardowego torsu i głowy")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Odchylenie standardowe")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
