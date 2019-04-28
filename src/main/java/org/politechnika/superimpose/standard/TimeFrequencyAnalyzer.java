package org.politechnika.superimpose.standard;

import org.politechnika.data_parser.model.TimeSequential;

import java.util.List;
import java.util.Objects;

class TimeFrequencyAnalyzer {

    private long sumOfTimeDifference = 0;
    private long minimalFoundValue = 0;
    private SeriesType mostFreqSeries;
    private StandardSuperimposedChart stdSuperHandle;

    SeriesType findSeriesWithMostFrequentChanges(StandardSuperimposedChart stdSuper) {
        sumOfTimeDifference = 0;
        minimalFoundValue = 0;
        stdSuperHandle = stdSuper;
        mostFreqSeries = null;

        checkIfKinectValuesHasMostFrequentTime();
        checkIfLeftGloveValuesHasMostFrequentTime();
        checkIfRightGloveValuesHasMostFrequentTime();
        checkIfPulsometerValuesHasMostFrequentTime();

        if (Objects.isNull(mostFreqSeries)) throw new IllegalStateException("All series are empty");
        return mostFreqSeries;
    }

    private void checkIfKinectValuesHasMostFrequentTime() {
        if (!stdSuperHandle.kinectValues.isEmpty()) {
            sumOfTimeDifference = calculateTimeDifferenceSum(stdSuperHandle.kinectValues, sumOfTimeDifference);
            sumOfTimeDifference /= stdSuperHandle.kinectValues.size();
            minimalFoundValue = sumOfTimeDifference;
            mostFreqSeries = SeriesType.KINECT;
        }
    }

    private void checkIfLeftGloveValuesHasMostFrequentTime() {
        if (!stdSuperHandle.leftGloveValues.isEmpty()) {
            sumOfTimeDifference = calculateTimeDifferenceSum(stdSuperHandle.leftGloveValues, sumOfTimeDifference);
            sumOfTimeDifference /= stdSuperHandle.leftGloveValues.size();
            if (isMoreFrequent(sumOfTimeDifference, minimalFoundValue)) {
                minimalFoundValue = sumOfTimeDifference;
                mostFreqSeries = SeriesType.LEFT_HAND;
            }
        }
    }

    private void checkIfRightGloveValuesHasMostFrequentTime() {
        if (!stdSuperHandle.rightGloveValues.isEmpty()) {
            sumOfTimeDifference = calculateTimeDifferenceSum(stdSuperHandle.rightGloveValues, sumOfTimeDifference);
            sumOfTimeDifference /= stdSuperHandle.rightGloveValues.size();
            if (isMoreFrequent(sumOfTimeDifference, minimalFoundValue)) {
                minimalFoundValue = sumOfTimeDifference;
                mostFreqSeries = SeriesType.RIGHT_HAND;
            }
        }
    }

    private void checkIfPulsometerValuesHasMostFrequentTime() {
        if (!stdSuperHandle.pulsometerValues.isEmpty()) {
            sumOfTimeDifference = calculateTimeDifferenceSum(stdSuperHandle.pulsometerValues, sumOfTimeDifference);
            sumOfTimeDifference /= stdSuperHandle.pulsometerValues.size();
            if (isMoreFrequent(sumOfTimeDifference, minimalFoundValue)) {
                mostFreqSeries = SeriesType.PULS;
            }
        }
    }

    private boolean isMoreFrequent(long timeDifferenceSum, long mostFreqVal) {
        return timeDifferenceSum < mostFreqVal || mostFreqVal == 0;
    }

    private long calculateTimeDifferenceSum(List<? extends TimeSequential> values, long timeDifferenceSum) {
        for (int i = 0; i < values.size() - 1; i++) {
            timeDifferenceSum
                    += values.get(i + 1).getTime().toEpochMilli()
                    - values.get(i).getTime().toEpochMilli();
        }
        return timeDifferenceSum;
    }
}
