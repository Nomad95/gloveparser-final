package org.politechnika.analysis.impl;

import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.politechnika.analysis.PreviousDataUsageStatisticsAnalyzer;
import org.politechnika.analysis.SimpleStatisticsAnalyzer;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;

import java.util.List;
import java.util.function.ToDoubleFunction;

/**
 * Chart data jest wartością zwracaną potrzebną do wykonania odpowiednich wykresów.
 * Wywołanie poszczególnych metod powinno odbywać się po kolei, gdyż każda kolejna metoda potrzebuje wyników poprzedniej
 * pierdu pierdu xD
 */
public class GloveStatisticsAnalyzerImpl implements SimpleStatisticsAnalyzer<GloveDataDto>, PreviousDataUsageStatisticsAnalyzer<GloveDataDto> {

    @Override
    public double getAverage(List<GloveDataDto> dtos, ToDoubleFunction<GloveDataDto> valueExtractor) {
        return dtos.stream()
                .mapToDouble(valueExtractor)
                .average()
                .orElse(0);
    }

    @Override
    public double getVariance(List<GloveDataDto> dtos, ToDoubleFunction<GloveDataDto> valueExtractor) {
        return new Variance().evaluate(
                dtos.stream().mapToDouble(valueExtractor).toArray(),
                getAverage(dtos, valueExtractor));
    }

    @Override
    public double getVariance(List<GloveDataDto> dtos, double mean, ToDoubleFunction<GloveDataDto> valueExtractor) {
        return new Variance().evaluate(
                dtos.stream().mapToDouble(valueExtractor).toArray(),
                mean);
    }

    @Override
    public double getStandardDeviation(List<GloveDataDto> dtos, ToDoubleFunction<GloveDataDto> valueExtractor) {
        return new StandardDeviation().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray(), getAverage(dtos, valueExtractor));
    }

    @Override
    public double getStandardDeviation(List<GloveDataDto> dtos, double mean, ToDoubleFunction<GloveDataDto> valueExtractor) {
        return new StandardDeviation().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray(), mean);
    }

    @Override
    public double getSkewness(List<GloveDataDto> dtos, ToDoubleFunction<GloveDataDto> valueExtractor) {
        double val = new Skewness().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray());
        return Double.isNaN(val) ? 0 : val;
    }

    @Override
    public double getKurtosis(List<GloveDataDto> dtos, ToDoubleFunction<GloveDataDto> valueExtractor) {
        double val = new Kurtosis().evaluate(dtos.stream().mapToDouble(valueExtractor).toArray());
        return Double.isNaN(val) ? 0 : val;
    }
}
