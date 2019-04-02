package org.politechnika.report.impl.glove_functions;

import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Scatter;
import org.politechnika.model.Finger;
import org.politechnika.model.TimeIntervalHandStatistics;

import java.util.function.Function;

public class CreateScatterChart implements Function<TimeIntervalHandStatistics, TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        new ChartGeneratorImpl().drawChart(
                new Scatter
                        .Builder()
                        .withFileName(handStatistics.getHandName() + "_hand_average_variance_scatter")
                        .addScatterDataSet(
                                handStatistics.getKurtosisValueDimensionForFinger(Finger.THUMB),
                                handStatistics.getAverageValueDimensionForFinger(Finger.THUMB),
                                "*")
                        .addScatterDataSet(
                                handStatistics.getKurtosisValueDimensionForFinger(Finger.INDEX),
                                handStatistics.getAverageValueDimensionForFinger(Finger.INDEX),
                                "*")
                        .addScatterDataSet(
                                handStatistics.getKurtosisValueDimensionForFinger(Finger.MIDDLE),
                                handStatistics.getAverageValueDimensionForFinger(Finger.MIDDLE),
                                "*")
                        .addScatterDataSet(
                                handStatistics.getKurtosisValueDimensionForFinger(Finger.RING),
                                handStatistics.getAverageValueDimensionForFinger(Finger.RING),
                                "*")
                        .addScatterDataSet(
                                handStatistics.getKurtosisValueDimensionForFinger(Finger.LITTLE),
                                handStatistics.getAverageValueDimensionForFinger(Finger.LITTLE),
                                "*")
                        .withGrid()
                        .withTitle("Wykres rozrzutu")
                        .withXAxisName("Srednia")
                        .withYAxisName("Wariancja")
                        .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                        .build(MainController.getDestinationSubFolder()));

        return handStatistics;
    }
}
