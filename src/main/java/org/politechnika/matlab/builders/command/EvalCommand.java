package org.politechnika.matlab.builders.command;

import com.mathworks.engine.MatlabEngine;

import java.util.concurrent.ExecutionException;

public interface EvalCommand {

    void evaluate(MatlabEngine matlabEngine) throws ExecutionException, InterruptedException;
}
