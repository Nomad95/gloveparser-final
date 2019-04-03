package org.politechnika.analysis.impl;

import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.politechnika.analysis.PreviousDataUsageStatisticsAnalyzer;
import org.politechnika.analysis.SimpleStatisticsAnalyzer;
import org.politechnika.data_parser.csv.definitions.beans.KinectDataDto;

import java.util.List;
import java.util.function.ToDoubleFunction;

public class KinectStatisticAnalyzerImpl implements SimpleStatisticsAnalyzer<KinectDataDto>, PreviousDataUsageStatisticsAnalyzer<KinectDataDto> {

    @Override
    public double getAverage(List<KinectDataDto> dtos, ToDoubleFunction<KinectDataDto> valueExtractor) {
        return dtos.stream()
                .mapToDouble(valueExtractor)
                .average()
                .orElse(0);
    }

    @Override
    public double getVariance(List<KinectDataDto> dtos, ToDoubleFunction<KinectDataDto> valueExtractor) {
        return new Variance().evaluate(
                dtos.stream().mapToDouble(valueExtractor).toArray(),
                getAverage(dtos, valueExtractor));
    }

    @Override
    public double getVariance(List<KinectDataDto> dtos, double mean, ToDoubleFunction<KinectDataDto> valueExtractor) {
        return new Variance().evaluate(
                dtos.stream().mapToDouble(valueExtractor).toArray(),
                mean);
    }

    @Override
    public double getStandardDeviation(List<KinectDataDto> dtos, ToDoubleFunction<KinectDataDto> valueExtractor) {
        return new StandardDeviation().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray(), getAverage(dtos, valueExtractor));
    }

    @Override
    public double getStandardDeviation(List<KinectDataDto> dtos, double mean, ToDoubleFunction<KinectDataDto> valueExtractor) {
        return new StandardDeviation().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray(), mean);
    }

    @Override
    public double getSkewness(List<KinectDataDto> dtos, ToDoubleFunction<KinectDataDto> valueExtractor) {
        return new Skewness().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray());
    }

    @Override
    public double getKurtosis(List<KinectDataDto> dtos, ToDoubleFunction<KinectDataDto> valueExtractor) {
        return new Kurtosis().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray());
    }
}
