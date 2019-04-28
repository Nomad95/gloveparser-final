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
public class CreateTimeSegmentSkewnessRightArmChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getSkewnessValue(Sensor.SHOULDER_RIGHT),
                                    handStatistics.getSkewnessValue(Sensor.ELBOW_RIGHT),
                                    handStatistics.getSkewnessValue(Sensor.HAND_RIGHT),
                                    handStatistics.getSkewnessValue(Sensor.THUMB_RIGHT),
                                    handStatistics.getSkewnessValue(Sensor.HAND_TIP_RIGHT)
                            }, handStatistics.getTimeDimension())
                            .withFileName("right_arm_sensors_skewnesses")
                            .withGrid()
                            .withLegend("{'Ramie', 'Łokieć', 'Dłoń', 'Kciuk', 'Palec wskazujący'}")
                            .withTitle("Wartości skośności prawej ręki")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Skośność")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentSkewnessRightArmChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}
