package org.politechnika.report.impl.glove_functions;

import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.Finger;
import org.politechnika.model.TimeIntervalHandStatistics;

import java.util.function.Function;

public class CreateTimeSegmentedSkewnessChart implements Function<TimeIntervalHandStatistics, TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getSkewnessValueDimensionForFinger(Finger.THUMB),
                                handStatistics.getSkewnessValueDimensionForFinger(Finger.INDEX),
                                handStatistics.getSkewnessValueDimensionForFinger(Finger.MIDDLE),
                                handStatistics.getSkewnessValueDimensionForFinger(Finger.RING),
                                handStatistics.getSkewnessValueDimensionForFinger(Finger.LITTLE)
                        }, handStatistics.getTimeDimension())
                        .withFileName(handStatistics.getHandName() + "_hand_skewness")
                        .withGrid()
                        .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                        .withTitle("Rękawica 5DT - Współczynnik skośności - ręka " + handStatistics.getPolishHandName())
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Wsp. Skośności")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
