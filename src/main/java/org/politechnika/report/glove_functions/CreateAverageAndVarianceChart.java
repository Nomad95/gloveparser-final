package org.politechnika.report.glove_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Scatter;
import org.politechnika.model.glove.Finger;
import org.politechnika.model.glove.TimeIntervalHandStatistics;

import java.util.function.Function;

@Slf4j
public class CreateAverageAndVarianceChart implements Function<TimeIntervalHandStatistics, TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        log.debug("Creating Average and Variance scatter chart");
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
                        .withTitle("Rękawica 5DT - Wykres rozrzutu - ręka " + handStatistics.getPolishHandName())
                        .withXAxisName("Srednia")
                        .withYAxisName("Wariancja")
                        .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                        .build(MainController.getDestinationSubFolder()));
        log.debug("Chart created");

        return handStatistics;
    }
}
