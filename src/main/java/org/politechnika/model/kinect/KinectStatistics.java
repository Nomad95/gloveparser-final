package org.politechnika.model.kinect;

public class KinectStatistics {

    private Kinect averageKinectData = new Kinect("Średnia arytmetyczna");
    private Kinect standardDeviationKinectData = new Kinect("Odchylenie standardowe");
    private Kinect varianceKinectData = new Kinect("Wariancja");
    private Kinect skewnessCoefficientKinectData = new Kinect("Współczynnik skośności");
    private Kinect kurtosisKinectData = new Kinect("Kurtoza");

    public void setAverageFor(Sensor sensor, Dimension dimension, double average) {
        averageKinectData.setValueFor(sensor, dimension, average);
    }

    public void setStandardDeviationFor(Sensor sensor, Dimension dimension, double standardDeviation) {
        standardDeviationKinectData.setValueFor(sensor, dimension, standardDeviation);
    }

    public void setVarianceFor(Sensor sensor, Dimension dimension, double variance) {
        varianceKinectData.setValueFor(sensor, dimension, variance);
    }

    public void setSkewnessCoefficientFor(Sensor sensor, Dimension dimension, double skewnessCoefficient) {
        skewnessCoefficientKinectData.setValueFor(sensor, dimension, skewnessCoefficient);
    }

    public void setKurtosisFor(Sensor sensor, Dimension dimension, double kurtosis) {
        kurtosisKinectData.setValueFor(sensor, dimension, kurtosis);
    }

    public String getAverageForFingerView(Sensor sensor, Dimension dimension) {
        return String.valueOf(averageKinectData.getValueFor(sensor, dimension));
    }

    public String getStandardDeviationForFingerView(Sensor sensor, Dimension dimension) {
        return String.valueOf(standardDeviationKinectData.getValueFor(sensor, dimension));
    }

    public String getVarianceForFingerView(Sensor sensor, Dimension dimension) {
        return String.valueOf(varianceKinectData.getValueFor(sensor, dimension));
    }

    public String getSkewnessForFingerView(Sensor sensor, Dimension dimension) {
        return String.valueOf(skewnessCoefficientKinectData.getValueFor(sensor, dimension));
    }

    public String getKurtosisForFingerView(Sensor sensor, Dimension dimension) {
        return String.valueOf(kurtosisKinectData.getValueFor(sensor, dimension));
    }
}
