package org.politechnika.processing;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.DoubleStream;

public class DoubleArrayTimeSeries {

    private Map<String, double[]> series;

    private List<String> keysInsertOrder = new ArrayList<>(25);

    public DoubleArrayTimeSeries() {
        this.series = new HashMap<>();
    }

    public void addSeries(String seriesName, double[] seriesArray) {
        series.put(seriesName, seriesArray);
        if (!keysInsertOrder.contains(seriesName))
            keysInsertOrder.add(seriesName);
    }

    public void alignArrays(AligningMode aligningMode) {
        if (series.isEmpty())
            throw new IllegalStateException("There is no arrays to align");

        int maxArrayLength = getMaxArrayLength();

        for (Map.Entry<String, double[]> seriesEntry : series.entrySet()) {
            if (seriesEntry.getValue().length != maxArrayLength) {
                final double valueToAdd = aligningMode.getValueToAlign(seriesEntry.getValue());
                if (maxArrayLength - seriesEntry.getValue().length == 1)
                    seriesEntry.setValue(ArrayUtils.add(seriesEntry.getValue(), valueToAdd));
                else {
                    double[] alignValues = DoubleStream.generate(() -> valueToAdd)
                            .limit(maxArrayLength - seriesEntry.getValue().length)
                            .toArray();
                    seriesEntry.setValue(ArrayUtils.addAll(seriesEntry.getValue(), alignValues));
                }
            }
        }
    }

    public double[] getSequencedTimeArray() {
        if (series.isEmpty())
            throw new IllegalStateException("There is no arrays to work on");

        int maxArrayLength = getMaxArrayLength();

        return DoubleStream.iterate(0, n -> n + 1).limit(maxArrayLength).toArray();
    }

    private int getMaxArrayLength() {
        int maxArrayLength = 0;
        for (double[] seriesValues : series.values()) {
            if (seriesValues.length > maxArrayLength)
                maxArrayLength = seriesValues.length;
        }
        return maxArrayLength;
    }

    public <T> void adjustListToSeriesLength(List<T> list) {
        if (series.isEmpty())
            throw new IllegalStateException("There is no arrays to work on");
        int maxArrayLength = getMaxArrayLength();
        if (list.size() != maxArrayLength && list.size() < maxArrayLength) {
            while (list.size() < maxArrayLength) {
                list.add(list.get(list.size() - 1));
            }
        }
    }

    public Map<String, double[]> getAllSeriesMap() {
        return new HashMap<>(series);
    }

    public double[] getSeries(String key) {
        return ArrayUtils.clone(series.getOrDefault(key, new double[0]));
    }

    public Object[] getAllSeriesArrays() {
        return series.values().toArray();
    }

    public String[] getInsertOrderKeys() {
        return keysInsertOrder.toArray(new String[0]);
    }

    public enum AligningMode {
        ZEROS {
            @Override
            double getValueToAlign(double[] arrayToAppendTo) {
                return 0;
            }
        }, LAST_VALUE {
            @Override
            double getValueToAlign(double[] arrayToAppendTo) {
                return arrayToAppendTo.length == 0 ? 0 : arrayToAppendTo[arrayToAppendTo.length - 1];
            }
        };

        abstract double getValueToAlign(double[] arrayToAppendTo);
    }
}
