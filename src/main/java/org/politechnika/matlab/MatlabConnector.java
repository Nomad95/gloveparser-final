package org.politechnika.matlab;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;

public interface MatlabConnector {

    MatlabEngine startSession() throws EngineException;

}
