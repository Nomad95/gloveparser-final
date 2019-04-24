package org.politechnika.superimpose.standard;

import lombok.RequiredArgsConstructor;
import org.politechnika.data_parser.model.TimeSequential;
import org.politechnika.model.glove.GloveValueDto;
import org.politechnika.model.kinect.PointDistanceValueDto;
import org.politechnika.model.pulsometer.PulsometerValueDto;

import java.time.Instant;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.function.DoublePredicate;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toCollection;
import static org.politechnika.superimpose.standard.SeriesType.*;

@RequiredArgsConstructor
class SeriesTransformer {

    private static final Supplier<IllegalStateException> NO_VALUES_EXISTS = () -> new IllegalStateException("Could not align to empty values!");
    private final SeriesInterpolator interpolatior;

    void transformSeriesToStartAtSameTimeAs(StandardSuperimposedChart stdSuper, SeriesType seriesType) {
        TimeSequential valueToAlignTo = getPointInTimeToAlignTo(stdSuper, seriesType);
        EnumSet<SeriesType> otherSeries = getNotEmptySeriesOtherThan(stdSuper, seriesType);
        for (SeriesType series : otherSeries) {
            if (KINECT.equals(series)) {
                alignFirstPointDistanceValueAndAdjustRest(stdSuper, valueToAlignTo);
            } else if (PULS.equals(series)) {
                alignFirstPulsValueAndAdjustRest(stdSuper, valueToAlignTo);
            } else if (LEFT_HAND.equals(series)) {
                alignFirstLeftGloveValueAndAdjustRest(stdSuper, valueToAlignTo);
            } else if (RIGHT_HAND.equals(series)) {
                alignFirstRightGloveValueAndAdjustRest(stdSuper, valueToAlignTo);
            }
        }
    }

    private TimeSequential getPointInTimeToAlignTo(StandardSuperimposedChart stdSuper, SeriesType seriesType) {
        return seriesType.getSeries(stdSuper).stream()
                .findFirst()
                .orElseThrow(NO_VALUES_EXISTS);
    }

    private EnumSet<SeriesType> getNotEmptySeriesOtherThan(StandardSuperimposedChart stdSuper, SeriesType seriesType) {
        EnumSet<SeriesType> otherSeries = EnumSet.allOf(SeriesType.class);
        otherSeries.removeIf(s -> s.getSeries(stdSuper).isEmpty());
        otherSeries.remove(seriesType);
        return otherSeries;
    }

    private void alignFirstPointDistanceValueAndAdjustRest(StandardSuperimposedChart stdSuper, TimeSequential valueToAlignTo) {
        PointDistanceValueDto first = stdSuper.kinectStream().findFirst().orElseThrow(NO_VALUES_EXISTS);
        long millisDiff = valueToAlignTo.getTime().toEpochMilli() - first.getTime().toEpochMilli();
        stdSuper.kinectValues = stdSuper.kinectStream()
                .map(e -> e.moveInTime(e.getTime().plusMillis(millisDiff)))
                .collect(toCollection(LinkedList::new));
    }

    private void alignFirstPulsValueAndAdjustRest(StandardSuperimposedChart stdSuper, TimeSequential valueToAlignTo) {
        PulsometerValueDto first = stdSuper.pulsometerStream().findFirst().orElseThrow(NO_VALUES_EXISTS);
        long millisDiff = valueToAlignTo.getTime().toEpochMilli() - first.getTime().toEpochMilli();
        stdSuper.pulsometerValues = stdSuper.pulsometerStream()
                .map(e -> e.moveInTime(e.getTime().plusMillis(millisDiff)))
                .collect(toCollection(LinkedList::new));
    }

    private void alignFirstLeftGloveValueAndAdjustRest(StandardSuperimposedChart stdSuper, TimeSequential valueToAlignTo) {
        GloveValueDto first = stdSuper.leftGloveStream().findFirst().orElseThrow(NO_VALUES_EXISTS);
        long millisDiff = valueToAlignTo.getTime().toEpochMilli() - first.getTime().toEpochMilli();
        stdSuper.leftGloveValues = stdSuper.leftGloveStream()
                .map(e -> e.moveInTime(e.getTime().plusMillis(millisDiff)))
                .collect(toCollection(LinkedList::new));
    }

    private void alignFirstRightGloveValueAndAdjustRest(StandardSuperimposedChart stdSuper, TimeSequential valueToAlignTo) {
        GloveValueDto first = stdSuper.rightGloveStream().findFirst().orElseThrow(NO_VALUES_EXISTS);
        long millisDiff = valueToAlignTo.getTime().toEpochMilli() - first.getTime().toEpochMilli();
        stdSuper.rightGloveValues = stdSuper.rightGloveStream()
                .map(e -> e.moveInTime(e.getTime().plusMillis(millisDiff)))
                .collect(toCollection(LinkedList::new));
    }

    void cutPulsometerValues(StandardSuperimposedChart stdSuper, DoublePredicate where) {
        stdSuper.pulsometerValues = stdSuper.pulsometerStream()
                .filter(v -> !where.test(v.getValue()))
                .collect(toCollection(LinkedList::new));
    }

    void cutTimeOfOtherSeriesToAlignToSeriesOfType(StandardSuperimposedChart stdSuper, SeriesType type) {
        if (type.getSeries(stdSuper).isEmpty())
            return;
        Instant start = type.getSeries(stdSuper).stream().findFirst().orElseThrow(NO_VALUES_EXISTS).getTime();
        Instant end = type.getSeries(stdSuper).get(type.getSeries(stdSuper).size() - 1).getTime();

        EnumSet<SeriesType> otherSeries = getNotEmptySeriesOtherThan(stdSuper, type);
        for (SeriesType series : otherSeries) {
            if (KINECT.equals(series)) {
                cutKinectTimeValues(stdSuper, start, end);
            } else if (PULS.equals(series)) {
                cutPulsTimeValues(stdSuper, start, end);
            } else if (LEFT_HAND.equals(series)) {
                cutLeftHandTimeValues(stdSuper, start, end);
            } else if (RIGHT_HAND.equals(series)) {
                cutRightTimeValues(stdSuper, start, end);
            }
        }
    }

    private void cutRightTimeValues(StandardSuperimposedChart stdSuper, Instant start, Instant end) {
        stdSuper.rightGloveValues = stdSuper.rightGloveStream()
                .filter(val -> val.getTime().isAfter(start) && val.getTime().isBefore(end))
                .collect(toCollection(LinkedList::new));
    }

    private void cutLeftHandTimeValues(StandardSuperimposedChart stdSuper, Instant start, Instant end) {
        stdSuper.leftGloveValues = stdSuper.leftGloveStream()
                .filter(val -> val.getTime().isAfter(start) && val.getTime().isBefore(end))
                .collect(toCollection(LinkedList::new));
    }

    private void cutPulsTimeValues(StandardSuperimposedChart stdSuper, Instant start, Instant end) {
        stdSuper.pulsometerValues = stdSuper.pulsometerStream()
                .filter(val -> val.getTime().isAfter(start) && val.getTime().isBefore(end))
                .collect(toCollection(LinkedList::new));
    }

    private void cutKinectTimeValues(StandardSuperimposedChart stdSuper, Instant start, Instant end) {
        stdSuper.kinectValues = stdSuper.kinectStream()
                .filter(val -> val.getTime().isAfter(start) && val.getTime().isBefore(end))
                .collect(toCollection(LinkedList::new));
    }

    void interpolateWithSeries(StandardSuperimposedChart stdSuper, SeriesType type) {
        ListIterator<PointDistanceValueDto> kinectLiterator = stdSuper.kinectValues.listIterator();
        ListIterator<PulsometerValueDto> pulsLiterator = stdSuper.pulsometerValues.listIterator();
        ListIterator<GloveValueDto> leftLiterator = stdSuper.leftGloveValues.listIterator();
        ListIterator<GloveValueDto> rightLiterator = stdSuper.rightGloveValues.listIterator();
        if (KINECT.equals(type)) {
            while (kinectLiterator.hasNext()) {
                PointDistanceValueDto current = kinectLiterator.next();
                interpolatior.traversePuls(pulsLiterator, current);
                interpolatior.traverseHand(leftLiterator, current);
                interpolatior.traverseHand(rightLiterator, current);
            }
        } else if (PULS.equals(type)) {
            while (pulsLiterator.hasNext()) {
                PulsometerValueDto current = pulsLiterator.next();
                interpolatior.traverseKinect(kinectLiterator, current);
                interpolatior.traverseHand(leftLiterator, current);
                interpolatior.traverseHand(rightLiterator, current);
            }
        } else if (LEFT_HAND.equals(type)) {
            while (leftLiterator.hasNext()) {
                GloveValueDto current = leftLiterator.next();
                interpolatior.traversePuls(pulsLiterator, current);
                interpolatior.traverseKinect(kinectLiterator, current);
                interpolatior.traverseHand(rightLiterator, current);
            }
        } else if (RIGHT_HAND.equals(type)) {
            while (rightLiterator.hasNext()) {
                GloveValueDto current = rightLiterator.next();
                interpolatior.traversePuls(pulsLiterator, current);
                interpolatior.traverseKinect(kinectLiterator, current);
                interpolatior.traverseHand(leftLiterator, current);
            }
        }
    }



}
