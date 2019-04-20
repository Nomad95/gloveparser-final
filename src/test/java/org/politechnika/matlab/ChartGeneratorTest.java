package org.politechnika.matlab;

import org.junit.Ignore;
import org.junit.Test;
import org.politechnika.commons.ParserMatlabException;
import org.politechnika.matlab.builders.MultiPlot;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.matlab.builders.Scatter;
import org.politechnika.matlab.builders.SubPlot;

public class ChartGeneratorTest {

    @Test
    @Ignore
    public void shouldSaveFile() throws ParserMatlabException {
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
    public void shouldSaveFileComplex() throws ParserMatlabException {
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

    @Test
    @Ignore
    public void shouldSaveScatter() throws ParserMatlabException {
        ChartGeneratorImpl chartGenerator = new ChartGeneratorImpl();

        double[] double11 = {0.123d, 0.23612d, 123.22d, 98.44d};
        double[] double12 = {44.123d, 1.53612d, 12.22d, 0.44d};

        double[] double21 = {22.123d, 55.23612d, 77.22d, 15.44d};
        double[] double22 = {4.123d, 96.53612d, 22.22d, 11.44d};

        chartGenerator.drawChart(new Scatter
                .Builder()
                .withFileName("Skejter")
                .addScatterDataSet(double11, double12, "*")
                .addScatterDataSet(double21, double22, "*")
                .withGrid()
        .withTitle("Scatter 1")
        .withXAxisName("Srednia")
        .withYAxisName("Wariancja")
        .withLegend("{'dupy', 'cycki'}")
        .build("D:\\\\"));

    }

}
