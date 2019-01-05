package org.politechnika.matlab.builders.command;

import com.mathworks.engine.MatlabEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
public class Figure implements EvalCommand {

    @Override
    public void evaluate(MatlabEngine matlabEngine) throws ExecutionException, InterruptedException {
        log.debug("Matlab: evaluated: figure()");
        matlabEngine.eval("figure('units','normalized','outerposition',[0 0 1 1]);set(gcf, 'Visible', 'off');", null,
                          null);
    }

    public static Figure newFigure() {
        return new Figure();
    }
}
