package org.politechnika.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.politechnika.commons.Separators.TAB;

@RequiredArgsConstructor
public class HandStatistics {

    @Getter
    private final String hand;

    private FingerStatistics averageGloveData = new FingerStatistics("Średnia arytmetyczna");
    private FingerStatistics standardDeviationGloveData = new FingerStatistics("Odchylenie standardowe");
    private FingerStatistics varianceGloveData = new FingerStatistics("Wariancja");
    private FingerStatistics skewnessCoefficientGloveData = new FingerStatistics("Współczynnik skośności");
    private FingerStatistics kurtosisGloveData = new FingerStatistics("Kurtoza");

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

    public String getAverageForFingerView(Finger finger) {
        return String.valueOf(averageGloveData.getValueFor(finger));
    }

    public String getStandardDeviationForFingerView(Finger finger) {
        return String.valueOf(standardDeviationGloveData.getValueFor(finger));
    }

    public String getVarianceForFingerView(Finger finger) {
        return String.valueOf(varianceGloveData.getValueFor(finger));
    }

    public String getSkewnessForFingerView(Finger finger) {
        return String.valueOf(skewnessCoefficientGloveData.getValueFor(finger));
    }

    public String getKurtosisForFingerView(Finger finger) {
        return String.valueOf(kurtosisGloveData.getValueFor(finger));
    }

    public void printToConsole() {
        System.out.println(hand);
        System.out.println("----------------------"
                + TAB + "Kciuk" + TAB + "Wskazujący" +TAB + "Srodkowy" + TAB + "Serdeczny " +TAB + "Mały");
        System.out.println(averageGloveData.toString());
        System.out.println(standardDeviationGloveData.toString());
        System.out.println(varianceGloveData.toString());
        System.out.println(skewnessCoefficientGloveData.toString());
        System.out.println(kurtosisGloveData.toString());
    }
}
