package org.politechnika.report.impl.glove_functions;

import org.politechnika.model.TimeIntervalHandStatistics;

import java.util.function.Function;

public class CreateTimeSegmentedVarianceChart implements Function<TimeIntervalHandStatistics, TimeIntervalHandStatistics> {

    @Override
    public TimeIntervalHandStatistics apply(TimeIntervalHandStatistics handStatistics) {
        //TODO: draw chart
        return handStatistics;
    }
}
