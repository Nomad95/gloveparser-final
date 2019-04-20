package org.politechnika.report.glove_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.ParserMatlabException;
import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.glove.Finger;
import org.politechnika.model.glove.TimeIntervalHandStatistics;

import java.util.function.Function;

@Slf4j
public class CreateTimeSegmentedAverageChart implements Function<TimeIntervalHandStatistics, TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        try {
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
        } catch (ParserMatlabException e) {
            log.error("Could not create TimeSegmentedAverageChart: {}", e.getMessage());
        }
        return handStatistics;
    }
}
