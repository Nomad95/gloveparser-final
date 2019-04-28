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
public class CreateTimeSegmentStandardDeviationRightArmChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getStandardDeviationValue(Sensor.SHOULDER_RIGHT),
                                    handStatistics.getStandardDeviationValue(Sensor.ELBOW_RIGHT),
                                    handStatistics.getStandardDeviationValue(Sensor.HAND_RIGHT),
                                    handStatistics.getStandardDeviationValue(Sensor.THUMB_RIGHT),
                                    handStatistics.getStandardDeviationValue(Sensor.HAND_TIP_RIGHT)
                            }, handStatistics.getTimeDimension())
                            .withFileName("right_arm_sensors_standard_deviation")
                            .withGrid()
                            .withLegend("{'Ramie', 'Łokieć', 'Dłoń', 'Kciuk', 'Palec wskazujący'}")
                            .withTitle("Wartości odchylenia standardowego prawej ręki")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Odchylenie standardowe")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentStandardDeviationRightArmChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}
