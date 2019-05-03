package org.politechnika.analysis;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.util.Precision;

public class StandardCorrelationAnalyzer implements CorrelationAnalyzer {

    @Override
    public double getPearsonCorrelation(double[] x, double[] y) {
        return Precision.round(new PearsonsCorrelation().correlation(x, y), 3);
    }
}
