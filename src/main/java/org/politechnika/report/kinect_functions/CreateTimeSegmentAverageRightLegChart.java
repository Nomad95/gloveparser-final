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
public class CreateTimeSegmentAverageRightLegChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getAverageValue(Sensor.WRIST_RIGHT),
                                    handStatistics.getAverageValue(Sensor.HIP_RIGHT),
                                    handStatistics.getAverageValue(Sensor.KNEE_RIGHT),
                                    handStatistics.getAverageValue(Sensor.FOOT_RIGHT)
                            }, handStatistics.getTimeDimension())
                            .withFileName("right_leg_sensors_averages")
                            .withGrid()
                            .withLegend("{'Talia', 'Biodro', 'Kolano', 'Stopa'}")
                            .withTitle("Średnie wartości prawej nogi")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Średnia")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentAverageRightLegChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}
