package org.politechnika.analysis;

import org.politechnika.data_parser.csv.definitions.DataDto;

import java.util.List;

public interface StatisticsAnalyzer<T extends DataDto> {

    double average(List<T> dtos);

    double variance(List<T> dtos);

}
