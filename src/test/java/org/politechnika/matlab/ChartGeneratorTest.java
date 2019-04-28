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

    @Test
    @Ignore
    public void shouldSaveSuperimposed() throws ParserMatlabException {
        ChartGeneratorImpl chartGenerator = new ChartGeneratorImpl();

        double[] double11 = {0.123d, 0.00002d, 0.22d, 0.44d, 0.33, 0.0000004, 0.000031};
        double[] double12 = {89.123d, 100.53612d, 120.22d, 110.44d};

        double[] double21 = {2200.123d, 2300.23612d, 2200.22d, 2399.44d};
        double[] double22 = {2300.123d, 2000.23612d, 1900.22d, 1799.44d};

        double[] time1 = new double[double11.length];
        for (int i = 0; i < time1.length; i++) {
            time1[i] = (i / 4.0D);
        }

        double[] time2 = new double[double12.length];
        for (int i = 0; i < time2.length; i++) {
            time2[i] = (i / 4.0D);
        }

//        chartGenerator.drawChart(new SuperimposedChart.Builder()
//                .withTitle("title1")
//                .withXAxisName("X1")
//                .withYAxisName("Y1")
//                .leftYaxis()
//                .addPulsometerPlot(double11, time1)
//                .holdOn()
//                .addGlovePlot(double21, time2)
//                .addGlovePlot(double22, time2)
//                .rightYaxis()
//                .addKinectPlot(double12, time2)
//                .build("D:\\\\"));

    }

}
