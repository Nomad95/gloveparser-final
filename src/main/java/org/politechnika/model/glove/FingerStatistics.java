package org.politechnika.model.glove;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.politechnika.commons.Separators.TAB;

@RequiredArgsConstructor
@Getter
public class FingerStatistics {

    private final String description;

    private double thumb;
    private double index;
    private double middle;
    private double ring;
    private double little;

    @Override
    public String toString() {
        return description + ":" + TAB +
                thumb + TAB +
                index + TAB +
                middle + TAB +
                ring + TAB +
                little;
    }

    void setValueFor(Finger finger, double value) {
        switch (finger) {
            case THUMB: thumb = value; return;
            case INDEX: index = value; return;
            case MIDDLE: middle = value; return;
            case RING: ring = value; return;
            case LITTLE: little = value; return;
        }

        throw new IllegalArgumentException("Tried to set value to an not mapped finger - " + finger);
    }

    double getValueFor(Finger finger) {
        switch (finger) {
            case THUMB: return thumb;
            case INDEX: return index;
            case MIDDLE: return middle;
            case RING:  return ring;
            case LITTLE: return little;
        }

        throw new IllegalArgumentException("Could not find value for finger " + finger);
    }
}
