package org.politechnika.model.kinect;

import com.google.common.collect.Lists;

import java.util.List;

public class TimeIntervalKinectStatistics {

    private List<KinectStatistics> statistics;

    public TimeIntervalKinectStatistics() {
        this.statistics = Lists.newArrayList();
    }

    public void addStatstics(KinectStatistics kinectStatistics) {
        statistics.add(kinectStatistics);
    }
}
