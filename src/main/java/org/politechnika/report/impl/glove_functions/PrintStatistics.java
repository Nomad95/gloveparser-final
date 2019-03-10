package org.politechnika.report.impl.glove_functions;

import org.politechnika.model.HandStatistics;

import java.util.function.Function;

public class PrintStatistics implements Function<HandStatistics, HandStatistics> {

    @Override
    public HandStatistics apply(HandStatistics handStatistics) {
        handStatistics.printToConsole();
        return handStatistics;
    }
}
