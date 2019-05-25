package org.politechnika.cache;

import lombok.Setter;
import org.politechnika.superimpose.Projection;

import java.util.Objects;

public enum ProjectionCache {
    I;

    @Setter
    private Projection projection;

    public Projection getProjection() {
        if (Objects.isNull(projection)) {
            IllegalStateException e = new IllegalStateException("Projection cache miss");
            ErrorCache.addError("Projection cache miss", e);
            throw e;
        }
        return projection;
    }
}
