package org.politechnika.model.kinect;

import com.google.common.collect.Lists;
import org.politechnika.commons.Constants;
import org.politechnika.frontend.main_controller.MainController;

import java.util.List;
import java.util.stream.IntStream;

public class TimeIntervalPointDistanceStatistics {

    private List<PointDistanceStatistics> statistics;

    public TimeIntervalPointDistanceStatistics() {
        this.statistics = Lists.newArrayList();
    }

    public void addStatstics(PointDistanceStatistics kinectStatistics) {
        statistics.add(kinectStatistics);
    }

    public double[] getTimeDimension(Sensor sensor) {
        return IntStream.range(0, statistics.size())
                .mapToDouble(value ->  ((double) value * (MainController.getTimeIntervalMillis() / Constants.MILLIS_IN_MINUTE)))
                .toArray();
    }

    public double[] getAverageValue(Sensor sensor) {
        return statistics.stream().mapToDouble(stats -> stats.getAverageForSensorView(sensor)).toArray();
    }

    public double[] getKurtosisValue(Sensor sensor) {
        return statistics.stream().mapToDouble(stats -> stats.getKurtosisForSensorView(sensor)).toArray();
    }

    public double[] getSkewnessValue(Sensor sensor) {
        return statistics.stream().mapToDouble(stats -> stats.getSkewnessForSensorView(sensor)).toArray();
    }

    public double[] getStandardDeviationValue(Sensor sensor) {
        return statistics.stream().mapToDouble(stats -> stats.getStandardDeviationForSensorView(sensor)).toArray();
    }

    public double[] getVarianceValue(Sensor sensor) {
        return statistics.stream().mapToDouble(stats -> stats.getVarianceForSensorView(sensor)).toArray();
    }
}
