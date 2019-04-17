package org.politechnika.analysis;

import org.politechnika.data_parser.csv.definitions.DataDto;

import java.util.List;
import java.util.function.ToDoubleFunction;

public interface SimpleStatisticsAnalyzer{

    <T extends DataDto> double getAverage(List<T> dtos, ToDoubleFunction<T> valueExtractor);

    <T extends DataDto> double getVariance(List<T> dtos, ToDoubleFunction<T> valueExtractor);

    <T extends DataDto> double getStandardDeviation(List<T> dtos, ToDoubleFunction<T> valueExtractor);

    <T extends DataDto> double getSkewness(List<T> dtos, ToDoubleFunction<T> valueExtractor);

    <T extends DataDto> double getKurtosis(List<T> dtos, ToDoubleFunction<T> valueExtractor);

}
