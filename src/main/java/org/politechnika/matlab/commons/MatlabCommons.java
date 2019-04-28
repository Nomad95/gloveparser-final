package org.politechnika.matlab.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Arrays;

import static java.util.Arrays.stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MatlabCommons {

    /**
     * Parses data sets and time array to the {time, data1, time, data2} form
     * Required to chart functions
     */
    public static Object[] getFunctionArguments(@NonNull Object[] dataSets, @NonNull double[] timeArray) {
        return stream(dataSets)
                .map(dataSet -> new Object[]{timeArray, dataSet})
                .flatMap(Arrays::stream)
                .toArray();
    }

    public static Object[] getFunctionArguments(@NonNull Object[] dataSets, @NonNull double[] timeArray, String markerColor) {
        Object[] resArr = getFunctionArguments(dataSets, timeArray);
        return  appendToArray(resArr,  markerColor );
    }

    public static Object[] appendToArray(Object[] array, Object obj) {
        Object[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[newArray.length - 1] = obj;
        return newArray;
    }
}
