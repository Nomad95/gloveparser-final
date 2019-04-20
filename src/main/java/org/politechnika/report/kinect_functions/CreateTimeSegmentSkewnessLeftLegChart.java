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
public class CreateTimeSegmentSkewnessLeftLegChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getSkewnessValue(Sensor.WRIST_LEFT),
                                    handStatistics.getSkewnessValue(Sensor.HIP_LEFT),
                                    handStatistics.getSkewnessValue(Sensor.KNEE_LEFT),
                                    handStatistics.getSkewnessValue(Sensor.FOOT_LEFT)
                            }, handStatistics.getTimeDimension())
                            .withFileName("left_leg_sensors_skewnesses")
                            .withGrid()
                            .withLegend("{'Talia', 'Biodro', 'Kolano', 'Stopa'}")
                            .withTitle("Wartości skośności lewej nogi")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Skośność")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentSkewnessLeftLegChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}
