package org.politechnika.report.glove_functions;

import org.politechnika.model.glove.TimeIntervalHandStatistics;

import java.util.function.UnaryOperator;

public class PrintTimeSegmentedStatistics implements UnaryOperator<TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        handStatistics.printToConsole();
        return handStatistics;
    }
}
