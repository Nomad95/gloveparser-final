package org.politechnika.model.kinect;

public class KinectStatistics {

    private KinectStatisticsSummary averageKinectValue = new KinectStatisticsSummary("Średnia arytmetyczna");
    private KinectStatisticsSummary standardDeviationKinectValue = new KinectStatisticsSummary("Odchylenie standardowe");
    private KinectStatisticsSummary varianceKinectValue = new KinectStatisticsSummary("Wariancja");
    private KinectStatisticsSummary skewnessCoefficientKinectValue = new KinectStatisticsSummary("Współczynnik skośności");
    private KinectStatisticsSummary kurtosisKinectValue = new KinectStatisticsSummary("Kurtoza");

    public void setAverageFor(Sensor sensor, Dimension dimension, double average) {
        averageKinectValue.setValueFor(sensor, dimension, average);
    }

    public void setStandardDeviationFor(Sensor sensor, Dimension dimension, double standardDeviation) {
        standardDeviationKinectValue.setValueFor(sensor, dimension, standardDeviation);
    }

    public void setVarianceFor(Sensor sensor, Dimension dimension, double variance) {
        varianceKinectValue.setValueFor(sensor, dimension, variance);
    }

    public void setSkewnessCoefficientFor(Sensor sensor, Dimension dimension, double skewnessCoefficient) {
        skewnessCoefficientKinectValue.setValueFor(sensor, dimension, skewnessCoefficient);
    }

    public void setKurtosisFor(Sensor sensor, Dimension dimension, double kurtosis) {
        kurtosisKinectValue.setValueFor(sensor, dimension, kurtosis);
    }
}
