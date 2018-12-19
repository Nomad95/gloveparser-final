package org.politechnika.analysis.impl;

import org.politechnika.analysis.PrimitiveStatisticsAnalyzer;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

public class PrimitiveStatisticsAnalyzerImpl implements PrimitiveStatisticsAnalyzer {

    @Override
    public double averageDouble(double[] doubleArray) {
        return Arrays.stream(requireNonNull(doubleArray)).average().orElse(0);
    }

    @Override
    public double averageInt(int[] intArray) {
        return Arrays.stream(requireNonNull(intArray)).average().orElse(0);
    }
}
