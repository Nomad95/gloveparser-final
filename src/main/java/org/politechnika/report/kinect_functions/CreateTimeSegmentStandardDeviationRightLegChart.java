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
public class CreateTimeSegmentStandardDeviationRightLegChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getStandardDeviationValue(Sensor.WRIST_RIGHT),
                                    handStatistics.getStandardDeviationValue(Sensor.HIP_RIGHT),
                                    handStatistics.getStandardDeviationValue(Sensor.KNEE_RIGHT),
                                    handStatistics.getStandardDeviationValue(Sensor.FOOT_RIGHT)
                            }, handStatistics.getTimeDimension())
                            .withFileName("right_leg_sensors_standard_deviation")
                            .withGrid()
                            .withLegend("{'Talia', 'Biodro', 'Kolano', 'Stopa'}")
                            .withTitle("Wartości odchylenia standardowego prawej nogi")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Odchylenie standardowe")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentStandardDeviationRightLegChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}

