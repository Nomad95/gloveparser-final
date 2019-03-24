package org.politechnika.report.impl.glove_functions;

import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.Finger;
import org.politechnika.model.TimeIntervalHandStatistics;

import java.util.function.Function;

public class CreateTimeSegmentedStandardDeviationChart implements Function<TimeIntervalHandStatistics, TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getStandardDeviationValueDimensionForFinger(Finger.THUMB),
                                handStatistics.getStandardDeviationValueDimensionForFinger(Finger.INDEX),
                                handStatistics.getStandardDeviationValueDimensionForFinger(Finger.MIDDLE),
                                handStatistics.getStandardDeviationValueDimensionForFinger(Finger.RING),
                                handStatistics.getStandardDeviationValueDimensionForFinger(Finger.LITTLE)
                        }, handStatistics.getTimeDimension())
                        .withFileName("left_hand_std_deviation")
                        .withGrid()
                        .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                        .withTitle("Odchylenie standardowe dla lewej ręki")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Odchylenie standardowe")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
