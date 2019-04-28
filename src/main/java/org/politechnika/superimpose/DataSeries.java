package org.politechnika.superimpose;

public interface DataSeries {

    /**
     * Time array
     */
    double[] getTimeArray();

    /**
     * 2D array of coupled data series
     */
    Object[] getDataArrays();
}
