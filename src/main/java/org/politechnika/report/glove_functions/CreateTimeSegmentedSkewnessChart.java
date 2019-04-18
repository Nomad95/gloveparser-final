package org.politechnika.report.glove_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.glove.Finger;
import org.politechnika.model.glove.TimeIntervalHandStatistics;

import java.util.function.UnaryOperator;

public class CreateTimeSegmentedSkewnessChart implements UnaryOperator<TimeIntervalHandStatistics> {

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
