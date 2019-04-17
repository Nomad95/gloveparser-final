package org.politechnika.report.impl.glove_functions;

import org.politechnika.model.glove.TimeIntervalHandStatistics;

import java.util.function.Function;

public class PrintTimeSegmentedStatistics implements Function<TimeIntervalHandStatistics, TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        handStatistics.printToConsole();
        return handStatistics;
    }
}
