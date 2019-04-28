package org.politechnika.superimpose.standard;

import org.politechnika.superimpose.Superimposed;

public class StandardSuperimposedChartFactory {

    public Superimposed getSuperimposedChartGenerator() {
        return new StandardSuperimposedChart(new TimeFrequencyAnalyzer(), new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation())));
    }
}
