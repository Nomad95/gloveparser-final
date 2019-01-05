package org.politechnika.matlab;

import org.politechnika.matlab.builders.ChartBuilder;

/**
 * Every function can throw internally:
 * - @{@link InterruptedException} - when some bad asynchronous shit happened,
 * - @{@link com.mathworks.engine.EngineException} - when Matlab fails to start,
 * - @{@link com.mathworks.engine.MatlabExecutionException } - when there is an error in Matlab command, or
 * - @{@link com.mathworks.engine.MatlabSyntaxException} - when there is an syntax error in Matlab command
 *
 */
public interface ChartGenerator {

    void drawChart(ChartBuilder builder);
}
