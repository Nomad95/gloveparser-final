package org.politechnika.report.impl.glove_functions;

import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.Finger;
import org.politechnika.model.TimeIntervalHandStatistics;

import java.util.function.Function;

public class CreateTimeSegmentedVarianceChart implements Function<TimeIntervalHandStatistics, TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                handStatistics.getVarianceValueDimensionForFinger(Finger.THUMB),
                                handStatistics.getVarianceValueDimensionForFinger(Finger.INDEX),
                                handStatistics.getVarianceValueDimensionForFinger(Finger.MIDDLE),
                                handStatistics.getVarianceValueDimensionForFinger(Finger.RING),
                                handStatistics.getVarianceValueDimensionForFinger(Finger.LITTLE)
                        }, handStatistics.getTimeDimension())
                        .withFileName(handStatistics.getHandName() + "_hand_variance")
                        .withGrid()
                        .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                        .withTitle("Wariancja")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Wariancja")
                        .build(MainController.getDestinationSubFolder()));
        return handStatistics;
    }
}
