package org.politechnika.matlab.builders;

import com.mathworks.engine.MatlabEngine;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.politechnika.matlab.builders.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.politechnika.matlab.commons.MatlabCommons.getFunctionArguments;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SuperimposedChart implements ChartBuilder {

    private List<EvalCommand> evalCommands;

    @Override
    public void drawChart(MatlabEngine engine) throws ExecutionException, InterruptedException {
        for (EvalCommand evalCommand : evalCommands) {
            evalCommand.evaluate(engine);
        }
    }

    public static class Builder {
        private List<EvalCommand> evalCommands;
        private String xAxisName;
        private String yAxisName;
        private String legend;
        private String title;
        private String fileName;

        public Builder() {
            this.evalCommands = new ArrayList<>(32);
            this.evalCommands.add(new Figure());
        }

        public Builder addPulsometerPlot(Object[] dataArrays, double[] timeArray) {
            evalCommands.add(new PlotFunction(getFunctionArguments(dataArrays, timeArray, "-b")));
            return this;
        }

        public Builder addGlovePlot(Object[] dataArrays, double[] timeArray) {
            evalCommands.add(new PlotFunction(getFunctionArguments(dataArrays, timeArray, "-g")));
            return this;
        }

        public Builder addKinectPlot(Object[] dataArrays, double[] timeArray) {
            evalCommands.add(new PlotFunction(getFunctionArguments(dataArrays, timeArray, "-r")));
            return this;
        }

        public Builder holdOn() {
            evalCommands.add(new HoldOn());
            return this;
        }

        public Builder leftYaxis() {
            evalCommands.add(new LeftYaxis());
            return this;
        }

        public Builder rightYaxis() {
            evalCommands.add(new RightYaxis());
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

        public SuperimposedChart build(@NonNull String savePath) {
            evalCommands.add(new SaveImage(savePath, this.fileName));
            return new SuperimposedChart(evalCommands);
        }
    }
}
