package org.politechnika.report.impl.glove_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.glove.Finger;
import org.politechnika.model.glove.TimeIntervalHandStatistics;

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
                        .withFileName(handStatistics.getHandName() + "_hand_kurtosis")
                        .withGrid()
                        .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                        .withTitle("Rękawica 5DT - Kurtoza - ręka " + handStatistics.getPolishHandName())
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Kurtoza")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
