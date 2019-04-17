package org.politechnika.analysis.impl;

import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.politechnika.analysis.PreviousDataUsageStatisticsAnalyzer;
import org.politechnika.analysis.SimpleStatisticsAnalyzer;
import org.politechnika.data_parser.csv.definitions.DataDto;

import java.util.List;
import java.util.function.ToDoubleFunction;


public class StandardStatisticsAnalyzerImpl implements SimpleStatisticsAnalyzer, PreviousDataUsageStatisticsAnalyzer {

    @Override
    public <T extends DataDto> double getAverage(List<T> dtos, ToDoubleFunction<T> valueExtractor) {
        return dtos.stream()
                .mapToDouble(valueExtractor)
                .average()
                .orElse(0);
    }

    @Override
    public <T extends DataDto> double getVariance(List<T> dtos, ToDoubleFunction<T> valueExtractor) {
        return new Variance().evaluate(
                dtos.stream().mapToDouble(valueExtractor).toArray(),
                getAverage(dtos, valueExtractor));
    }

    @Override
    public <T extends DataDto> double getVariance(List<T> dtos, double mean, ToDoubleFunction<T> valueExtractor) {
        return new Variance().evaluate(
                dtos.stream().mapToDouble(valueExtractor).toArray(),
                mean);
    }

    @Override
    public <T extends DataDto> double getStandardDeviation(List<T> dtos, ToDoubleFunction<T> valueExtractor) {
        return new StandardDeviation().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray(), getAverage(dtos, valueExtractor));
    }

    @Override
    public <T extends DataDto> double getStandardDeviation(List<T> dtos, double mean, ToDoubleFunction<T> valueExtractor) {
        return new StandardDeviation().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray(), mean);
    }

    @Override
    public <T extends DataDto> double getSkewness(List<T> dtos, ToDoubleFunction<T> valueExtractor) {
        double val = new Skewness().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray());
        return Double.isNaN(val) ? 0 : val;
    }

    @Override
    public <T extends DataDto> double getKurtosis(List<T> dtos, ToDoubleFunction<T> valueExtractor) {
        double val = new Kurtosis().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray());
        return Double.isNaN(val) ? 0 : val;
    }
}
