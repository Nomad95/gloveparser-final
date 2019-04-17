package org.politechnika.analysis;

import org.politechnika.data_parser.csv.definitions.DataDto;

import java.util.List;
import java.util.function.ToDoubleFunction;

public interface PreviousDataUsageStatisticsAnalyzer {

    <T extends DataDto> double getVariance(List<T> dtos, double mean, ToDoubleFunction<T> valueExtractor);

    <T extends DataDto> double getStandardDeviation(List<T> dtos, double mean, ToDoubleFunction<T> valueExtractor);
}
