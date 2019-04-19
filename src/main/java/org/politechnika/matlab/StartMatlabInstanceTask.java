package org.politechnika.matlab;

import com.mathworks.engine.EngineException;
import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartMatlabInstanceTask extends Task<Void> {

    @Override
    protected Void call() throws Exception {
        log.debug("Starting matlab instance...");
        try {
            MatlabSessionFactory.getMatlabSession();
        } catch (EngineException e) {
            //todo: wyswietl error
            log.error("Could not start matlab instance", e);
        }
        log.debug("Started matlab instance");

        return null;
    }
}
