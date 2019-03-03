package org.politechnika.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HandData {

    @Getter
    private final String hand;

    private FingerData averageGloveData = new FingerData("Średnia arytmetyczna dla całego pliku");
    private FingerData standardDeviationGloveData = new FingerData("Odchylenie standardowe dla całego pliku");
    private FingerData varianceGloveData = new FingerData("Wariancja dla całego pliku");
    private FingerData skewnessCoefficientGloveData = new FingerData("Współczynnik skośności dla całego pliku");
    private FingerData kurtosisGloveData = new FingerData("Kurtoza dla całego pliku");

    public void setAverageFor(Finger finger, double average) {
        averageGloveData.setValueFor(finger, average);
    }

    public void setStandardDeviationFor(Finger finger, double standardDeviation) {
        standardDeviationGloveData.setValueFor(finger, standardDeviation);
    }

    public void setVarianceFor(Finger finger, double variance) {
        varianceGloveData.setValueFor(finger, variance);
    }

    public void setSkewnessCoefficientFor(Finger finger, double skewnessCoefficient) {
        skewnessCoefficientGloveData.setValueFor(finger, skewnessCoefficient);
    }

    public void setKurtosisFor(Finger finger, double kurtosis) {
        kurtosisGloveData.setValueFor(finger, kurtosis);
    }
}
