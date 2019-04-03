package org.politechnika.model.kinect;

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

    public double getAverageForSensorView(Sensor sensor) {
        return averageKinectData.getValueFor(sensor);
    }

    public double getStandardDeviationForSensorView(Sensor sensor) {
        return standardDeviationKinectData.getValueFor(sensor);
    }

    public double getVarianceForSensorView(Sensor sensor) {
        return varianceKinectData.getValueFor(sensor);
    }

    public double getSkewnessForSensorView(Sensor sensor) {
        return skewnessCoefficientKinectData.getValueFor(sensor);
    }

    public double getKurtosisForSensorView(Sensor sensor) {
        return kurtosisKinectData.getValueFor(sensor);
    }
}
