package org.politechnika.report.superimposed_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.ParserMatlabException;
import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGenerator;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.SuperimposedChart;
import org.politechnika.superimpose.DataSeries;
import org.politechnika.superimpose.standard.SuperimposedChartBundle;

@Slf4j
public class DrawSuperimposedChart {

    public void drawSuperimposedChart(SuperimposedChartBundle bundle) {
        ChartGenerator generator = new ChartGeneratorImpl();

        SuperimposedChart.Builder builder = new SuperimposedChart.Builder()
                .withTitle("Nałożony wykres")
                .withFileName("superimposed_chart")
                .withXAxisName("Wartości")
                .withYAxisName("Czas [s]")
                .leftYaxis()
                .holdOn();

        builder = builder.rightYaxis();

        for (DataSeries kinecto : bundle.getKinectSeries()) {
            builder = builder.addKinectPlot(kinecto.getDataArrays(), kinecto.getTimeArray());
        }

        builder = builder.leftYaxis();

        for (DataSeries glovo : bundle.getGloveSeries()) {
            builder = builder.addGlovePlot(glovo.getDataArrays(), glovo.getTimeArray());
        }

        for (DataSeries pulso : bundle.getPulsometerSeries()) {
            builder = builder.addPulsometerPlot(pulso.getDataArrays(), pulso.getTimeArray());
        }

        builder.withLegend("{'Rękawica Lewa', 'Rękawica Prawa', 'Pulsometr', 'Kinect'}");

        SuperimposedChart build = builder.build(MainController.getDestinationSubFolder());
        try {
            generator.drawChart(build);
        } catch (ParserMatlabException e) {
            log.error("Could not create SuperimposedChart: {}", e.getMessage());
        }
    }
}
