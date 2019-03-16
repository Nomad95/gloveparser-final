package org.politechnika.matlab;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class StandardMatlabConnector implements MatlabConnector {

    @Override
    public MatlabEngine startSession() throws EngineException {
        try {
            log.info("Starting MatLab instance...");
            MatlabEngine session = MatlabEngine.startMatlab();
            log.info("done");
            return session;
        }  catch (InterruptedException e) {
            log.error("Connecting to MatLab was interrupted by another thread", e);
        }

        throw new EngineException("Unable to start MatLab");
    }
}
