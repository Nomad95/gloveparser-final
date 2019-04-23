package org.politechnika.superimpose.standard;

import lombok.RequiredArgsConstructor;
import org.politechnika.data_parser.model.TimeSequential;
import org.politechnika.model.glove.GloveValueDto;
import org.politechnika.model.kinect.PointDistanceValueDto;
import org.politechnika.model.pulsometer.PulsometerValueDto;

import java.util.ListIterator;

@RequiredArgsConstructor
class SeriesInterpolator {

    private final DirectLinearInterpolation interpolation;

    void traversePuls(ListIterator<PulsometerValueDto> pulsLiterator, TimeSequential current) {
        while (pulsLiterator.hasNext()) {
            PulsometerValueDto next = pulsLiterator.next();
            if (next.getTime().isBefore(current.getTime()))
                continue;
            pulsLiterator.previous();
            if (!pulsLiterator.hasPrevious())
                break;
            PulsometerValueDto prev = pulsLiterator.previous();
            pulsLiterator.add(interpolation.interpolatePuls(current, next, prev));
            pulsLiterator.next();
            break;
        }
    }

    void traverseHand(ListIterator<GloveValueDto> handLiterator, TimeSequential current) {
        while (handLiterator.hasNext()) {
            GloveValueDto next = handLiterator.next();
            if (next.getTime().isBefore(current.getTime()))
                continue;
            handLiterator.previous();
            if (!handLiterator.hasPrevious())
                break;
            GloveValueDto prev = handLiterator.previous();
            handLiterator.add(interpolation.interpolateHand(current, next, prev));
            handLiterator.next();
            break;
        }
    }

    void traverseKinect(ListIterator<PointDistanceValueDto> kinectLiterator, TimeSequential current) {
        while (kinectLiterator.hasNext()) {
            PointDistanceValueDto next = kinectLiterator.next();
            if (next.getTime().isBefore(current.getTime()))
                continue;
            kinectLiterator.previous();
            if (!kinectLiterator.hasPrevious())
                break;
            PointDistanceValueDto prev = kinectLiterator.previous();
            kinectLiterator.add(interpolation.interpolateKinect(current, next, prev));
            kinectLiterator.next();
            break;
        }
    }

}
