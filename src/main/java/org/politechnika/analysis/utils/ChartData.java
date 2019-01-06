package org.politechnika.analysis.utils;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class ChartData {
    double[] time;
    double[] finger1;
    double[] finger2;
    double[] finger3;
    double[] finger4;
    double[] finger5;

    public ChartData(int length) {
        this.time = new double[length];
        this.finger1 = new double[length];
        this.finger2 = new double[length];
        this.finger3 = new double[length];
        this.finger4 = new double[length];
        this.finger5 = new double[length];
    }

    public void setTimeElem(int index, double value) {
        time[index] = value;
    }

    public void setFinger1Elem(int index, double value) {
        finger1[index] = value;
    }

    public void setFinger2Elem(int index, double value) {
        finger2[index] = value;
    }

    public void setFinger3Elem(int index, double value) {
        finger3[index] = value;
    }

    public void setFinger4Elem(int index, double value) {
        finger4[index] = value;
    }

    public void setFinger5Elem(int index, double value) {
        finger5[index] = value;
    }

    public double getTimeElem(int index) {
        return time[index];
    }

    public double getFinger1Elem(int index) {
        return finger1[index];
    }

    public double getFinger2Elem(int index) {
        return finger2[index];
    }

    public double getFinger3Elem(int index) {
        return finger3[index];
    }

    public double getFinger4Elem(int index) {
        return finger4[index];
    }

    public double getFinger5Elem(int index) {
        return finger5[index];
    }

}
