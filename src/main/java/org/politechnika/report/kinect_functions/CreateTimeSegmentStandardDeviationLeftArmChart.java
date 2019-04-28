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
public class CreateTimeSegmentStandardDeviationLeftArmChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
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
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentStandardDeviationLeftArmChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}
