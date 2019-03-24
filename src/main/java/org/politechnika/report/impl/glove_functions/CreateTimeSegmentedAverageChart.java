package org.politechnika.report.impl.glove_functions;

import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.Finger;
import org.politechnika.model.TimeIntervalHandStatistics;

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
                .withFileName("left_hand_average")
                .withGrid()
                .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                .withTitle("Średnie wartości dla lewej ręki")
                .withXAxisName("Czas [s]")
                .withYAxisName("Średnia")
                .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
