package org.politechnika.superimpose;

public interface Projection {
    boolean cutPulsometer();
    boolean startAtSameTime();
    boolean endAtSameTime();
    boolean cutOtherToAlignToPulsometer();
    boolean cleanData();
}
