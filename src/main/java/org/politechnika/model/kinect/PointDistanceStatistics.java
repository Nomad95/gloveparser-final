package org.politechnika.model.kinect;

import org.politechnika.commons.Separators;

import java.util.StringJoiner;

import static org.politechnika.commons.Separators.NEWLINE;
import static org.politechnika.commons.Separators.TAB;

public class PointDistanceStatistics {
    
    private PointDistance averageKinectData = new PointDistance("Średnia arytmetyczna");
    private PointDistance standardDeviationKinectData = new PointDistance("Odchylenie standardowe");
    private PointDistance varianceKinectData = new PointDistance("Wariancja");
    private PointDistance skewnessCoefficientKinectData = new PointDistance("Współczynnik skośności");
    private PointDistance kurtosisKinectData = new PointDistance("Kurtoza");

    public void setAverageFor(Sensor sensor,  double average) {
        averageKinectData.setValueFor(sensor, average);
    }

    public void setStandardDeviationFor(Sensor sensor,  double standardDeviation) {
        standardDeviationKinectData.setValueFor(sensor, standardDeviation);
    }

    public void setVarianceFor(Sensor sensor,  double variance) {
        varianceKinectData.setValueFor(sensor, variance);
    }

    public void setSkewnessCoefficientFor(Sensor sensor,  double skewnessCoefficient) {
        skewnessCoefficientKinectData.setValueFor(sensor, skewnessCoefficient);
    }

    public void setKurtosisFor(Sensor sensor,  double kurtosis) {
        kurtosisKinectData.setValueFor(sensor, kurtosis);
    }

    double getAverageForSensorView(Sensor sensor) {
        return averageKinectData.getValueFor(sensor);
    }

    double getStandardDeviationForSensorView(Sensor sensor) {
        return standardDeviationKinectData.getValueFor(sensor);
    }

    double getVarianceForSensorView(Sensor sensor) {
        return varianceKinectData.getValueFor(sensor);
    }

    double getSkewnessForSensorView(Sensor sensor) {
        return skewnessCoefficientKinectData.getValueFor(sensor);
    }

    double getKurtosisForSensorView(Sensor sensor) {
        return kurtosisKinectData.getValueFor(sensor);
    }

    public String toReportString() {
        StringBuilder averagesSb = new StringBuilder();
        StringBuilder stdDevSb = new StringBuilder();
        StringBuilder varianceSb = new StringBuilder();
        StringBuilder skewnessSb = new StringBuilder();
        StringBuilder kurtosisSb = new StringBuilder();
        StringJoiner header = new StringJoiner(", ", "[ ", " ]");

        for (Sensor sensor : Sensor.values()) {
            averagesSb.append(Separators.TAB).append(getAverageForSensorView(sensor));
            stdDevSb.append(Separators.TAB).append(getStandardDeviationForSensorView(sensor));
            varianceSb.append(Separators.TAB).append(getVarianceForSensorView(sensor));
            skewnessSb.append(Separators.TAB).append(getSkewnessForSensorView(sensor));
            kurtosisSb.append(Separators.TAB).append(getKurtosisForSensorView(sensor));
            header.add(sensor.name());
        }

        return new StringBuilder(NEWLINE).append(header.toString()).append(NEWLINE)
                .append("Srednia arytmetyczna: ").append(TAB).append(averagesSb.toString()).append(NEWLINE)
                .append("Odchylenie standardowe: ").append(TAB).append(stdDevSb.toString()).append(NEWLINE)
                .append("Wariancja: ").append(TAB).append(varianceSb.toString()).append(NEWLINE)
                .append("Wsp. Skośności: ").append(TAB).append(skewnessSb.toString()).append(NEWLINE)
                .append("Kurtoza: ").append(TAB).append(kurtosisSb.toString()).append(NEWLINE).append(NEWLINE).toString();
    }
}
