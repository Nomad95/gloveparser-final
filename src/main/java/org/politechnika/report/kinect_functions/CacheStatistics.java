package org.politechnika.report.kinect_functions;

import org.politechnika.cache.LoadingStringCache;
import org.politechnika.model.kinect.PointDistanceStatistics;

import java.util.function.UnaryOperator;

import static org.politechnika.cache.LoadingStringCache.EntryType.KINECT_STATS;

public class CacheStatistics implements UnaryOperator<PointDistanceStatistics> {

    @Override
    public PointDistanceStatistics apply(PointDistanceStatistics pointDistanceStatistics) {
        LoadingStringCache.put(KINECT_STATS, pointDistanceStatistics.toReportString());
        return pointDistanceStatistics;
    }
}
