package org.politechnika.report.impl.glove_functions;

import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.glove.Finger;
import org.politechnika.model.glove.TimeIntervalHandStatistics;

import java.util.function.Function;

public class CreateTimeSegmentedAverageChart implements Function<TimeIntervalHandStatistics, TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getAverageValueDimensionForFinger(Finger.THUMB),
                                handStatistics.getAverageValueDimensionForFinger(Finger.INDEX),
                                handStatistics.getAverageValueDimensionForFinger(Finger.MIDDLE),
                                handStatistics.getAverageValueDimensionForFinger(Finger.RING),
                                handStatistics.getAverageValueDimensionForFinger(Finger.LITTLE)
                        }, handStatistics.getTimeDimension())
                .withFileName(handStatistics.getHandName() + "_hand_average")
                .withGrid()
                .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                .withTitle("Rękawica 5DT - Średnie wartości - ręka " + handStatistics.getPolishHandName())
                .withXAxisName("Czas [s]")
                .withYAxisName("Średnia")
                .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
