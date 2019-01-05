package org.politechnika.matlab;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import lombok.AllArgsConstructor;
import org.politechnika.matlab.builders.ChartBuilder;

import java.util.concurrent.ExecutionException;


@AllArgsConstructor
public class ChartGeneratorImpl implements ChartGenerator {

    @Override
    public void drawChart(ChartBuilder builder) {
        MatlabEngine session;
        try {
            session = MatlabSessionFactory.getMatlabSession();
            builder.drawChart(session);
        } catch (EngineException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //TODO: show user an error
    }

}
