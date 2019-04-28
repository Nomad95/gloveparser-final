package org.politechnika.superimpose.standard;

import lombok.Value;
import org.politechnika.superimpose.DataSeries;

@Value
class StandardDataSeries implements DataSeries {
    double[] timeArray;
    /**
     * 2D array of coupled data series
     */
    Object[] dataArrays;
}
