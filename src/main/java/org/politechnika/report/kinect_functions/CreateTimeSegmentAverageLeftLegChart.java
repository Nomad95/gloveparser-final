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
public class CreateTimeSegmentAverageLeftLegChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getAverageValue(Sensor.WRIST_LEFT),
                                    handStatistics.getAverageValue(Sensor.HIP_LEFT),
                                    handStatistics.getAverageValue(Sensor.KNEE_LEFT),
                                    handStatistics.getAverageValue(Sensor.FOOT_LEFT)
                            }, handStatistics.getTimeDimension())
                            .withFileName("left_leg_sensors_averages")
                            .withGrid()
                            .withLegend("{'Talia', 'Biodro', 'Kolano', 'Stopa'}")
                            .withTitle("Średnie wartości lewej nogi")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Średnia")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create time segmented average left leg chart {}", e.getMessage());
        }
        return handStatistics;
    }
}
