package org.politechnika.analysis.impl;

import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.politechnika.analysis.PreviousDataUsageStatisticsAnalyzer;
import org.politechnika.analysis.SimpleStatisticsAnalyzer;
import org.politechnika.data_parser.csv.definitions.beans.PulsometerDataDto;

import java.util.List;
import java.util.function.ToDoubleFunction;

public class PulsometerStatisticAnalyzerImpl implements SimpleStatisticsAnalyzer<PulsometerDataDto>, PreviousDataUsageStatisticsAnalyzer<PulsometerDataDto> {

    @Override
    public double getAverage(List<PulsometerDataDto> dtos, ToDoubleFunction<PulsometerDataDto> valueExtractor) {
        return dtos.stream()
                   .mapToDouble(valueExtractor)
                   .average()
                   .orElse(0);
    }

    @Override
    public double getVariance(List<PulsometerDataDto> dtos, ToDoubleFunction<PulsometerDataDto> valueExtractor) {
        return new Variance().evaluate(
                dtos.stream().mapToDouble(valueExtractor).toArray(),
                getAverage(dtos, valueExtractor));
    }

    @Override
    public double getVariance(List<PulsometerDataDto> dtos, double mean, ToDoubleFunction<PulsometerDataDto> valueExtractor) {
        return new Variance().evaluate(
                dtos.stream().mapToDouble(valueExtractor).toArray(),
                mean);
    }

    @Override
    public double getStandardDeviation(List<PulsometerDataDto> dtos, ToDoubleFunction<PulsometerDataDto> valueExtractor) {
        return new StandardDeviation().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray(), getAverage(dtos, valueExtractor));
    }

    @Override
    public double getStandardDeviation(List<PulsometerDataDto> dtos, double mean, ToDoubleFunction<PulsometerDataDto> valueExtractor) {
        return new StandardDeviation().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray(), mean);
    }

    @Override
    public double getSkewness(List<PulsometerDataDto> dtos, ToDoubleFunction<PulsometerDataDto> valueExtractor) {
        return new Skewness().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray());
    }

    @Override
    public double getKurtosis(List<PulsometerDataDto> dtos, ToDoubleFunction<PulsometerDataDto> valueExtractor) {
        return new Kurtosis().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray());
    }
}
