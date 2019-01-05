package org.politechnika.matlab;

import org.junit.Ignore;
import org.junit.Test;
import org.politechnika.matlab.builders.MultiPlot;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.matlab.builders.SubPlot;

public class ChartGeneratorTest {

    @Test
    @Ignore
    public void shouldSaveFile() {
        ChartGeneratorImpl chartGenerator = new ChartGeneratorImpl();

        double[] doubles = {0.123d, 0.23612d, 123.22d, 98.44d};
//        double[] double2 = {44.123d, 1.53612d, 12.22d, 0.44d};

        double[] s = new double[doubles.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = (i / 4.0D);
        }

        chartGenerator.drawChart(new Plot.Builder(new Object[]{doubles}, s)
                .withFileName("test")
                .withGrid()
                .withLegend("{'legend','elo'}")
                .withTitle("title")
                .withXAxisName("X")
                .withYAxisName("Y")
                .build("D:\\\\"));
    }


    @Test
    @Ignore
    public void shouldSaveFileComplex() {
        ChartGeneratorImpl chartGenerator = new ChartGeneratorImpl();

        double[] doubles = {0.123d, 0.23612d, 123.22d, 98.44d};
        double[] double2 = {44.123d, 1.53612d, 12.22d, 0.44d};

        double[] s = new double[doubles.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = (i / 4.0D);
        }

        chartGenerator.drawChart(new MultiPlot
                .MultiPlotBuilder(2, 1)
                .withFileName("multi")
                .nextSubPlot(new SubPlot.Builder(new Object[]{doubles}, s)
                        .withGrid()
                        .withLegend("{'legend1'}")
                        .withTitle("title1")
                        .withXAxisName("X1")
                        .withYAxisName("Y1")
                        .build())
                .nextSubPlot(new SubPlot.Builder(new Object[]{double2}, s)
                        .withGrid()
                        .withLegend("{'legend2'}")
                        .withTitle("title2")
                        .withXAxisName("X2")
                        .withYAxisName("Y2")
                        .build())
                .build("D:\\\\"));

    }

}
