package org.politechnika.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.politechnika.commons.Separators.TAB;

@RequiredArgsConstructor
@Getter
public class FingerData {

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

    public void setValueFor(Finger finger, double value) {
        switch (finger) {
            case THUMB: thumb = value; return;
            case INDEX: index = value; return;
            case MIDDLE: middle = value; return;
            case RING: ring = value; return;
            case LITTLE: little = value; return;
        }
    }
}
