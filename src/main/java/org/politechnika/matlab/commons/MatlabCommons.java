package org.politechnika.matlab.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Arrays;

import static java.util.Arrays.stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MatlabCommons {

    public static Object[] getFunctionArguments(@NonNull Object[] dataSets, @NonNull double[] timeArray) {
        return stream(dataSets)
                .map(dataSet -> new Object[]{timeArray, dataSet})
                .flatMap(Arrays::stream)
                .toArray();
    }
}
