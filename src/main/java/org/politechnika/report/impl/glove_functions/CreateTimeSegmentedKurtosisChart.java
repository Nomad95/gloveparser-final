package org.politechnika.report.impl.glove_functions;

import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.Finger;
import org.politechnika.model.TimeIntervalHandStatistics;

import java.util.function.Function;

public class CreateTimeSegmentedKurtosisChart implements Function<TimeIntervalHandStatistics, TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getKurtosisValueDimensionForFinger(Finger.THUMB),
                                handStatistics.getKurtosisValueDimensionForFinger(Finger.INDEX),
                                handStatistics.getKurtosisValueDimensionForFinger(Finger.MIDDLE),
                                handStatistics.getKurtosisValueDimensionForFinger(Finger.RING),
                                handStatistics.getKurtosisValueDimensionForFinger(Finger.LITTLE)
                        }, handStatistics.getTimeDimension())
                        .withFileName("left_hand_kurtosis")
                        .withGrid()
                        .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                        .withTitle("Kurtoza dla lewej ręki")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Kurtoza")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
