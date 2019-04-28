package org.politechnika.report.glove_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.ParserMatlabException;
import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.glove.Finger;
import org.politechnika.model.glove.TimeIntervalHandStatistics;

import java.util.function.UnaryOperator;

@Slf4j
public class CreateTimeSegmentedStandardDeviationChart implements UnaryOperator<TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            new Object[]{
                                    handStatistics.getStandardDeviationValueDimensionForFinger(Finger.THUMB),
                                    handStatistics.getStandardDeviationValueDimensionForFinger(Finger.INDEX),
                                    handStatistics.getStandardDeviationValueDimensionForFinger(Finger.MIDDLE),
                                    handStatistics.getStandardDeviationValueDimensionForFinger(Finger.RING),
                                    handStatistics.getStandardDeviationValueDimensionForFinger(Finger.LITTLE)
                            }, handStatistics.getTimeDimension())
                            .withFileName(handStatistics.getHandName() + "_hand_std_deviation")
                            .withGrid()
                            .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                            .withTitle("Rękawica 5DT - Odchylenie standardowe - ręka " + handStatistics.getPolishHandName())
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Odchylenie standardowe")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentedStandardDeviationChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}
