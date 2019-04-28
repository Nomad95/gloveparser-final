package org.politechnika.report.glove_functions;

import org.politechnika.cache.EntryType;
import org.politechnika.cache.LoadingStringCache;
import org.politechnika.model.glove.HandStatistics;

import java.util.function.UnaryOperator;

public class CacheStatistics implements UnaryOperator<HandStatistics> {

    @Override
    public HandStatistics apply(HandStatistics handStatistics) {
        if ("left".equals(handStatistics.getHand()))
            LoadingStringCache.put(EntryType.LEFT_HAND_STATS, handStatistics.toReportString());
        else
            LoadingStringCache.put(EntryType.RIGHT_HAND_STATS, handStatistics.toReportString());

        return handStatistics;
    }
}
