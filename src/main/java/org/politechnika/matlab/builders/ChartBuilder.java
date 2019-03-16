package org.politechnika.matlab.builders;

import com.mathworks.engine.MatlabEngine;

import java.util.concurrent.ExecutionException;


/**
 * All commands should be added like in matlab command line - in proper order
 */
@FunctionalInterface
public interface ChartBuilder {

    void drawChart(MatlabEngine engine) throws ExecutionException, InterruptedException;
}
