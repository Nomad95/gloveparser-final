package org.politechnika.matlab;

import com.mathworks.engine.MatlabEngine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.ParserMatlabException;
import org.politechnika.matlab.builders.ChartBuilder;

import java.util.concurrent.ExecutionException;

@Slf4j
@AllArgsConstructor
public class ChartGeneratorImpl implements ChartGenerator {

    @Override
    public void drawChart(ChartBuilder builder) throws ParserMatlabException {
        MatlabEngine session;
        try {
            session = MatlabSessionFactory.getMatlabSession();
            builder.drawChart(session);
        } catch (InterruptedException | ExecutionException e) {
            log.error("Engine Error while creating matlab session", e);
            throw new ParserMatlabException("Could not start matlab engine", e);
        }
    }

}
