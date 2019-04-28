package org.politechnika.report.glove_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.ParserMatlabException;
import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.glove.Finger;
import org.politechnika.model.glove.TimeIntervalHandStatistics;

import java.util.function.UnaryOperator;

@Slf4j
public class CreateTimeSegmentedVarianceChart implements UnaryOperator<TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        try {
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
                            .withTitle("Rękawica 5DT - Wariancja - ręka " + handStatistics.getPolishHandName())
                            .withXAxisName("Czas [s]")
                            .withYAxisName("Wariancja")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentedVarianceChart. {}", e.getMessage());
        }
        return handStatistics;
    }
}
