package org.politechnika.matlab.builders;

import com.mathworks.engine.MatlabEngine;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.politechnika.matlab.builders.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.politechnika.matlab.commons.MatlabCommons.getFunctionArguments;

@RequiredArgsConstructor
public class SubPlot implements ChartBuilder {

    private final List<EvalCommand> evalCommands;

    @Override
    public void drawChart(MatlabEngine engine) throws ExecutionException, InterruptedException {
        for (EvalCommand evalCommand : evalCommands) {
            evalCommand.evaluate(engine);
        }
    }

    public static class Builder {
        private List<EvalCommand> evalCommands;
        private final Object[] dataSets;
        private final double[] timeArray;
        private String xAxisName;
        private String yAxisName;
        private String legend;
        private String title;
        private String fileName;

        public Builder(@NonNull Object[] dataSets, @NonNull double[] timeArray) {
            this.dataSets = dataSets;
            this.timeArray = timeArray;
            this.evalCommands = new ArrayList<>(5);
            this.evalCommands.add(new PlotFunction(getFunctionArguments(dataSets, timeArray)));
        }

        public Builder withXAxisName(String xAxisName) {
            evalCommands.add(new XLabel(xAxisName));
            return this;
        }

        public Builder withYAxisName(String yAxisName) {
            evalCommands.add(new YLabel(yAxisName));
            return this;
        }

        public Builder withLegend(String legend) {
            evalCommands.add(new Legend(legend));
            return this;
        }

        public Builder withTitle(String title) {
            evalCommands.add(new Title(title));
            return this;
        }

        public Builder withGrid() {
            evalCommands.add(new Grid());
            return this;
        }

        public SubPlot build() {
            return new SubPlot(evalCommands);
        }
    }

}
