package org.politechnika.matlab.builders.command;

import com.mathworks.engine.MatlabEngine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
@AllArgsConstructor
public class HoldOn implements EvalCommand {

    @Override
    public void evaluate(MatlabEngine matlabEngine) throws ExecutionException, InterruptedException {
        log.debug("Matlab: evaluated: hold on()");
        matlabEngine.eval("hold on");
    }
}
