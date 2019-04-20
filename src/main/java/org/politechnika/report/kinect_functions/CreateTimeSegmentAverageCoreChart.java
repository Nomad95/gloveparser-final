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
public class CreateTimeSegmentAverageCoreChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getAverageValue(Sensor.HEAD),
                                    handStatistics.getAverageValue(Sensor.NECK),
                                    handStatistics.getAverageValue(Sensor.SPINE_SHOULDER),
                                    handStatistics.getAverageValue(Sensor.SPINE_MID),
                                    handStatistics.getAverageValue(Sensor.SPINE_BASE)
                            }, handStatistics.getTimeDimension())
                            .withFileName("core_sensors_average")
                            .withGrid()
                            .withLegend("{'Głowa', 'Szyja', 'Odcinek szyjny kręgosłupa', 'Odcinek  środkowy kręgosłupa', 'Odcinek lędźwiowy kręgosłupa'}")
                            .withTitle("Średnie wartości torsu i głowy")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Średnia")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create time segmented average core chart. {}", e.getMessage());
        }
        return handStatistics;
    }
}
