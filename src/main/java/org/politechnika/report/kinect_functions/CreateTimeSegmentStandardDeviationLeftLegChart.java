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
public class CreateTimeSegmentStandardDeviationLeftLegChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getStandardDeviationValue(Sensor.WRIST_LEFT),
                                    handStatistics.getStandardDeviationValue(Sensor.HIP_LEFT),
                                    handStatistics.getStandardDeviationValue(Sensor.KNEE_LEFT),
                                    handStatistics.getStandardDeviationValue(Sensor.FOOT_LEFT)
                            }, handStatistics.getTimeDimension())
                            .withFileName("left_leg_sensors_standard_deviation")
                            .withGrid()
                            .withLegend("{'Talia', 'Biodro', 'Kolano', 'Stopa'}")
                            .withTitle("Wartości odchylenia standardowego lewej nogi")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Odchylenie standardowe")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentStandardDeviationLeftLegChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}
