package org.politechnika.report.glove_functions;

import org.politechnika.model.glove.HandStatistics;

import java.util.function.Function;

public class PrintStatistics implements Function<HandStatistics, HandStatistics> {

    @Override
    public HandStatistics apply(HandStatistics handStatistics) {
        handStatistics.printToConsole();
        return handStatistics;
    }
}
