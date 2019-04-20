package org.politechnika.report.pulsometer_functions;

import org.politechnika.cache.LoadingStringCache;
import org.politechnika.model.pulsometer.PulsometerStatistics;

import java.util.function.UnaryOperator;

import static org.politechnika.cache.LoadingStringCache.EntryType.PULS_STATS;

public class CacheStatistics implements UnaryOperator<PulsometerStatistics> {

    @Override
    public PulsometerStatistics apply(PulsometerStatistics pulsometerStatistics) {
        LoadingStringCache.put(PULS_STATS, pulsometerStatistics.toReportString());
        return pulsometerStatistics;
    }
}
