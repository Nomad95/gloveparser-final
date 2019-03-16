package org.politechnika.matlab.builders;

import com.mathworks.engine.MatlabEngine;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.politechnika.matlab.builders.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Scatter implements ChartBuilder {

    private List<EvalCommand> evalCommands;

    @Override
    public void drawChart(MatlabEngine engine) throws ExecutionException, InterruptedException {
        for (EvalCommand evalCommand : evalCommands) {
            evalCommand.evaluate(engine);
        }
    }

    public static class Builder {
        private List<EvalCommand> evalCommands;
        private List<ScatterFunction> scatterFunctions;
        private String xAxisName;
        private String yAxisName;
        private String legend;
        private String title;
        private String fileName;

        public Builder() {
            this.evalCommands = new ArrayList<>(16);
            this.scatterFunctions = new ArrayList<>(8);
            this.evalCommands.add(new Figure());
        }

        public Builder addScatterDataSet(@NonNull double[] xValueArray, @NonNull double[] yValueArray, String icon) {
            if (!scatterFunctions.isEmpty()) {
                evalCommands.add(new HoldOn());
            }
            ScatterFunction scatterFun = new ScatterFunction(new Object[]{yValueArray, xValueArray, icon});
            scatterFunctions.add(scatterFun);
            evalCommands.add(scatterFun);
            return this;
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

        public Builder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder withGrid() {
            evalCommands.add(new Grid());
            return this;
        }

        public Builder withLogaritmicScaleForYAxis() {
            evalCommands.add(new LogarithmicYAxis());
            return this;
        }

        public Scatter build(@NonNull String savePath) {
            evalCommands.add(new SaveImage(savePath, this.fileName));
            return new Scatter(evalCommands);
        }
    }
}
