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
public class CreateTimeSegmentSkewnessLeftArmChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getSkewnessValue(Sensor.SHOULDER_LEFT),
                                    handStatistics.getSkewnessValue(Sensor.ELBOW_LEFT),
                                    handStatistics.getSkewnessValue(Sensor.HAND_LEFT),
                                    handStatistics.getSkewnessValue(Sensor.THUMB_LEFT),
                                    handStatistics.getSkewnessValue(Sensor.HAND_TIP_LEFT)
                            }, handStatistics.getTimeDimension())
                            .withFileName("left_arm_sensors_skewnesses")
                            .withGrid()
                            .withLegend("{'Ramie', 'Łokieć', 'Dłoń', 'Kciuk', 'Palec wskazujący'}")
                            .withTitle("Wartości skośności lewej ręki")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Skośność")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentSkewnessLeftArmChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}
