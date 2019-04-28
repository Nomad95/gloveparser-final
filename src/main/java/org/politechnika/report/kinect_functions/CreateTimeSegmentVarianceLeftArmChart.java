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
public class CreateTimeSegmentVarianceLeftArmChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getVarianceValue(Sensor.SHOULDER_LEFT),
                                    handStatistics.getVarianceValue(Sensor.ELBOW_LEFT),
                                    handStatistics.getVarianceValue(Sensor.HAND_LEFT),
                                    handStatistics.getVarianceValue(Sensor.THUMB_LEFT),
                                    handStatistics.getVarianceValue(Sensor.HAND_TIP_LEFT)
                            }, handStatistics.getTimeDimension())
                            .withFileName("left_arm_sensors_variances")
                            .withGrid()
                            .withLegend("{'Ramie', 'Łokieć', 'Dłoń', 'Kciuk', 'Palec wskazujący'}")
                            .withTitle("Wartości wariancji lewej ręki")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Wariancja")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentVarianceLeftArmChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}
