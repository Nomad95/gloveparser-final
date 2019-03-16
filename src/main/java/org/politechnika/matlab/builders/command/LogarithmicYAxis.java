package org.politechnika.matlab.builders.command;

import com.mathworks.engine.MatlabEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
public class LogarithmicYAxis implements EvalCommand {

    @Override
    public void evaluate(MatlabEngine matlabEngine) throws ExecutionException, InterruptedException {
        log.debug("Matlab: evaluated: set(log)");
        matlabEngine.eval("set(gca,'yscale','log')", null, null);
    }
}
