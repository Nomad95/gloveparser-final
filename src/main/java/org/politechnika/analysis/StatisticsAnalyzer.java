package org.politechnika.analysis;

import org.politechnika.analysis.utils.ChartData;
import org.politechnika.data_parser.csv.definitions.DataDto;

import java.util.List;
import java.util.function.ToDoubleFunction;

public interface StatisticsAnalyzer<T extends DataDto> {

    double getAverage(List<T> dtos, ToDoubleFunction<T> valueExtractor);

    List<T> averageDataInOneSensor(List<T> dtos);

    ChartData calculateAverageDataFromFingers(ChartData average);

    ChartData averageOneFingerEvery1secFromHand(List<T> groupedData, String hand);

    ChartData varianceOneFingerEvery1secFromHand(List<T> groupedData, ChartData varianceFor1s,  String hand);

    ChartData standardDeviationEveryFingerEvery1sec(ChartData varianceEvery1s);

}
