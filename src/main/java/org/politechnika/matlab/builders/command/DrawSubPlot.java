package org.politechnika.matlab.builders.command;

import com.mathworks.engine.MatlabEngine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
@AllArgsConstructor
public class DrawSubPlot implements EvalCommand {

    private int horizontalDimensions;
    private int verticalDimensions;
    private int plotNumber;

    @Override
    public void evaluate(MatlabEngine matlabEngine) throws ExecutionException, InterruptedException {
        log.debug("Matlab: evaluated: subplot()");
        matlabEngine.eval("subplot(" + verticalDimensions + "," + horizontalDimensions + "," + plotNumber + ");", null, null);
    }

    public static DrawSubPlot subPlotOf(int horizontalDimensions, int verticalDimensions, int plotNumber) {
        return new DrawSubPlot(horizontalDimensions, verticalDimensions, plotNumber);
    }
}
