package org.politechnika.superimpose.standard;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SuperimposedChartBundle {

    private List<StandardDataSeries> pulsometerSeries;
    private List<StandardDataSeries> gloveSeries;
    private List<StandardDataSeries> kinectSeries;

    SuperimposedChartBundle() {
        this.pulsometerSeries = new ArrayList<>(1);
        this.gloveSeries = new ArrayList<>(2);
        this.kinectSeries = new ArrayList<>(5);
    }

    void addPulsometerSeries(StandardDataSeries dataSeries) {
        pulsometerSeries.add(dataSeries);
    }

    void addGloveSeries(StandardDataSeries dataSeries) {
        gloveSeries.add(dataSeries);
    }

    void addKinectSeries(StandardDataSeries dataSeries) {
        kinectSeries.add(dataSeries);
    }
}
