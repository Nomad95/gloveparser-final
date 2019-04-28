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
public class CreateTimeSegmentVarianceRightLegChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getVarianceValue(Sensor.WRIST_RIGHT),
                                    handStatistics.getVarianceValue(Sensor.HIP_RIGHT),
                                    handStatistics.getVarianceValue(Sensor.KNEE_RIGHT),
                                    handStatistics.getVarianceValue(Sensor.FOOT_RIGHT)
                            }, handStatistics.getTimeDimension())
                            .withFileName("right_leg_sensors_variances")
                            .withGrid()
                            .withLegend("{'Talia', 'Biodro', 'Kolano', 'Stopa'}")
                            .withTitle("Wartości wariancji prawej nogi")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Wariancja")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentVarianceRightArmChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}
