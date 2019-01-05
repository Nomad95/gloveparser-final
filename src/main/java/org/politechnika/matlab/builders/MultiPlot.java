package org.politechnika.matlab.builders;

import com.mathworks.engine.MatlabEngine;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.politechnika.matlab.builders.command.DrawSubPlot.subPlotOf;
import static org.politechnika.matlab.builders.command.Figure.newFigure;
import static org.politechnika.matlab.builders.command.SaveImage.saveImageAs;

@Slf4j
public class MultiPlot implements ChartBuilder {

    private List<ChartBuilder> subPlots;
    private String filePath;
    private String fileName;
    private int horizontalDimensions;
    private int verticalDimensions;

    private MultiPlot(List<ChartBuilder> subPlots, String filePath, String fileName, int horizontalDimensions,
            int verticalDimensions) {
        this.subPlots = subPlots;
        this.fileName = fileName;
        this.filePath = filePath;
        this.horizontalDimensions = horizontalDimensions;
        this.verticalDimensions = verticalDimensions;
    }

    @Override
    public void drawChart(MatlabEngine engine) throws ExecutionException, InterruptedException {
        newFigure().evaluate(engine);
        for (int i = 0; i < subPlots.size(); i++) {
            subPlotOf(horizontalDimensions, verticalDimensions, i + 1).evaluate(engine);
            subPlots.get(i).drawChart(engine);
        }
        saveImageAs(filePath, fileName).evaluate(engine);
    }

    public static class MultiPlotBuilder {

        private List<ChartBuilder> subPlots;
        private int subPlotsLeft;
        private int horizontalDimensions;
        private int verticalDimensions;
        private String fileName;

        public MultiPlotBuilder(int horizontalDimensions, int verticalDimensions) {
            if (horizontalDimensions <= 0 || verticalDimensions <= 0) throw new IllegalArgumentException("Dimensions should be positive");
            this.horizontalDimensions = horizontalDimensions;
            this.verticalDimensions = verticalDimensions;
            this.subPlotsLeft = horizontalDimensions * verticalDimensions;
            this.subPlots = new ArrayList<>(this.subPlotsLeft);
        }

        public MultiPlotBuilder nextSubPlot(ChartBuilder subPlotBuilder) {
            if (subPlotsLeft == 0) {
                log.warn("Plots in MultiPlotBuilder has exceeded the dimension in matrix");
                return this;
            }
            this.subPlots.add(subPlotBuilder);
            return this;
        }

        public MultiPlotBuilder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public MultiPlot build(@NonNull String filePath) {
            return new MultiPlot(subPlots, filePath, fileName, horizontalDimensions, verticalDimensions);
        }

    }


}
