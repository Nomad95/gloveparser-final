package org.politechnika.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.politechnika.superimpose.Projection;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProjection implements Projection {
    private boolean cutPulsometer;
    private boolean startAtSameTime;
    private boolean endAtSameTime;
    private boolean cleanData;

    @Override
    public boolean cutPulsometer() {
        return cutPulsometer;
    }

    @Override
    public boolean startAtSameTime() {
        return startAtSameTime;
    }

    @Override
    public boolean endAtSameTime() {
        return endAtSameTime;
    }

    @Override
    public boolean cutOtherToAlignToPulsometer() {
        return cutPulsometer;
    }

    @Override
    public boolean cleanData() {
        return cleanData;
    }
}
