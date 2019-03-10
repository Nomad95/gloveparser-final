package org.politechnika.analysis;

import org.politechnika.data_parser.csv.definitions.DataDto;

import java.util.List;
import java.util.function.ToDoubleFunction;

public interface SimpleStatisticsAnalyzer<T extends DataDto> {

    double getAverage(List<T> dtos, ToDoubleFunction<T> valueExtractor);

    double getVariance(List<T> dtos, ToDoubleFunction<T> valueExtractor);

    double getStandardDeviation(List<T> dtos, ToDoubleFunction<T> valueExtractor);

    double getSkewness(List<T> dtos, ToDoubleFunction<T> valueExtractor);

    double getKurtosis(List<T> dtos, ToDoubleFunction<T> valueExtractor);

}
