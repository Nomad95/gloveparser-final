package org.politechnika.matlab;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
public class MatlabSessionFactory {

    private static MatlabEngine session;
    private static MatlabConnector matlabConnector;
    private static boolean isActive;

    static MatlabEngine getMatlabSession() throws EngineException {
        if (isNull(matlabConnector))
            matlabConnector = new StandardMatlabConnector();
        if (isNull(session)) {
            session = matlabConnector.startSession();
            isActive = true;
            return session;
        }
        if (!isActive) {
            session = matlabConnector.startSession();
            return session;
        }
        return session;
    }

    public static void changeConnectorProvider(MatlabConnector connector) {
        log.info("Changing matlab connector");
        if (isActive) {
            throw new IllegalStateException("Cannot change service provider while session is opened");
        }
        matlabConnector = connector;
        log.info("Matlab connector was changed");
    }

    public static void closeMatlabSession() {
        log.info("Closing existing matlab session");
        if (isActive) {
            try {
                session.close();
            } catch (EngineException e) {
                log.error("Could not close the session");
            }
        }
        log.info("Matlab session was closed");
    }

}
