package org.politechnika.report.impl.kinect_functions;

import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.Function;

public class CreateTimeSegmentVarianceCoreChart implements Function<TimeIntervalPointDistanceStatistics, TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getVarianceValue(Sensor.HEAD),
                                handStatistics.getVarianceValue(Sensor.NECK),
                                handStatistics.getVarianceValue(Sensor.SPINE_SHOULDER),
                                handStatistics.getVarianceValue(Sensor.SPINE_MID),
                                handStatistics.getVarianceValue(Sensor.SPINE_BASE)
                        }, handStatistics.getTimeDimension())
                        .withFileName("core_sensors_variances")
                        .withGrid()
                        .withLegend("{'Głowa', 'Szyja', 'Odcinek szyjny kręgosłupa', 'Odcinek  środkowy kręgosłupa', 'Odcinek lędźwiowy kręgosłupa'}")
                        .withTitle("Wartości wariancji torsu i głowy")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Wariancja")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
