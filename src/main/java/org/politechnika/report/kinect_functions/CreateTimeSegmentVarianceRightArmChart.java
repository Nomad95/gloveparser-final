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
public class CreateTimeSegmentVarianceRightArmChart implements UnaryOperator<TimeIntervalPointDistanceStatistics> {

    @Override
    public TimeIntervalPointDistanceStatistics apply(TimeIntervalPointDistanceStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getVarianceValue(Sensor.SHOULDER_RIGHT),
                                    handStatistics.getVarianceValue(Sensor.ELBOW_RIGHT),
                                    handStatistics.getVarianceValue(Sensor.HAND_RIGHT),
                                    handStatistics.getVarianceValue(Sensor.THUMB_RIGHT),
                                    handStatistics.getVarianceValue(Sensor.HAND_TIP_RIGHT)
                            }, handStatistics.getTimeDimension())
                            .withFileName("right_arm_sensors_variances")
                            .withGrid()
                            .withLegend("{'Ramie', 'Łokieć', 'Dłoń', 'Kciuk', 'Palec wskazujący'}")
                            .withTitle("Wartości wariancji prawej ręki")
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Wariancja")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentVarianceRightArmChart: {}", e.getMessage());
        }

        return handStatistics;
    }
}
