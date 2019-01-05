package org.politechnika.matlab.builders;

import com.mathworks.engine.MatlabEngine;

import java.util.concurrent.ExecutionException;

@FunctionalInterface
public interface ChartBuilder {

    void drawChart(MatlabEngine engine) throws ExecutionException, InterruptedException;
}
