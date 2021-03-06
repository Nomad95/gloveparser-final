package org.politechnika.report.kinect_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.ParserMatlabException;
import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.kinect.Sensor;
import org.politechnika.model.kinect.TimeIntervalPointDistanceStatistics;

import java.util.function.UnaryOperator;

@Slf4j
public class CreateTimeSegmentSkewnessCoreChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getSkewnessValue(Sensor.HEAD),
                                    handStatistics.getSkewnessValue(Sensor.NECK),
                                    handStatistics.getSkewnessValue(Sensor.SPINE_SHOULDER),
                                    handStatistics.getSkewnessValue(Sensor.SPINE_MID),
                                    handStatistics.getSkewnessValue(Sensor.SPINE_BASE)
                            }, handStatistics.getTimeDimension())
                            .withFileName("core_sensors_skewnesses")
                            .withGrid()
                            .withLegend("{'Głowa', 'Szyja', 'Odcinek szyjny kręgosłupa', 'Odcinek  środkowy kręgosłupa', 'Odcinek lędźwiowy kręgosłupa'}")
                            .withTitle("Wartości skośności torsu i głowy")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Skośność")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentSkewnessCoreChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}
