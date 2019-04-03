package org.politechnika.analysis.impl;

import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.politechnika.analysis.PreviousDataUsageStatisticsAnalyzer;
import org.politechnika.analysis.SimpleStatisticsAnalyzer;
import org.politechnika.model.kinect.PointDistance;

import java.util.List;
import java.util.function.ToDoubleFunction;

public class KinectPointDistanceAnalyzerImpl implements SimpleStatisticsAnalyzer<PointDistance>, PreviousDataUsageStatisticsAnalyzer<PointDistance> {

    @Override
    public double getAverage(List<PointDistance> dtos, ToDoubleFunction<PointDistance> valueExtractor) {
        return dtos.stream()
                .mapToDouble(valueExtractor)
                .average()
                .orElse(0);
    }

    @Override
    public double getVariance(List<PointDistance> dtos, ToDoubleFunction<PointDistance> valueExtractor) {
        return new Variance().evaluate(
                dtos.stream().mapToDouble(valueExtractor).toArray(),
                getAverage(dtos, valueExtractor));
    }

    @Override
    public double getVariance(List<PointDistance> dtos, double mean, ToDoubleFunction<PointDistance> valueExtractor) {
        return new Variance().evaluate(
                dtos.stream().mapToDouble(valueExtractor).toArray(),
                mean);
    }

    @Override
    public double getStandardDeviation(List<PointDistance> dtos, ToDoubleFunction<PointDistance> valueExtractor) {
        return new StandardDeviation().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray(), getAverage(dtos, valueExtractor));
    }

    @Override
    public double getStandardDeviation(List<PointDistance> dtos, double mean, ToDoubleFunction<PointDistance> valueExtractor) {
        return new StandardDeviation().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray(), mean);
    }

    @Override
    public double getSkewness(List<PointDistance> dtos, ToDoubleFunction<PointDistance> valueExtractor) {
        return new Skewness().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray());
    }

    @Override
    public double getKurtosis(List<PointDistance> dtos, ToDoubleFunction<PointDistance> valueExtractor) {
        return new Kurtosis().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray());
    }
}
