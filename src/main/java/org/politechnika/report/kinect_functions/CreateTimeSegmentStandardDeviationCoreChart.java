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
public class CreateTimeSegmentStandardDeviationCoreChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getStandardDeviationValue(Sensor.SHOULDER_LEFT),
                                    handStatistics.getStandardDeviationValue(Sensor.ELBOW_LEFT),
                                    handStatistics.getStandardDeviationValue(Sensor.HAND_LEFT),
                                    handStatistics.getStandardDeviationValue(Sensor.THUMB_LEFT),
                                    handStatistics.getStandardDeviationValue(Sensor.HAND_TIP_LEFT)
                            }, handStatistics.getTimeDimension())
                            .withFileName("left_arm_sensors_standard_deviation")
                            .withGrid()
                            .withLegend("{'Ramie', 'Łokieć', 'Dłoń', 'Kciuk', 'Palec wskazujący'}")
                            .withTitle("Wartości odchylenia standardowego lewej ręki")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Odchylenie standardowe")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentStandardDeviationCoreChart: {}");
        }
        return handStatistics;
    }
}
