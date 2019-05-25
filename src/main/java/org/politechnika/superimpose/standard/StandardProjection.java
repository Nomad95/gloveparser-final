package org.politechnika.superimpose.standard;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.politechnika.superimpose.Projection;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class StandardProjection implements Projection {

    @Override
    public boolean cutPulsometer() {
        return true;
    }

    @Override
    public boolean startAtSameTime() {
        return false;
    }

    @Override
    public boolean endAtSameTime() {
        return false;
    }

    @Override
    public boolean cutOtherToAlignToPulsometer() {
        return true;
    }

    @Override
    public boolean cleanData() {
        return false;
    }
}
