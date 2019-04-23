package org.politechnika.superimpose.standard;

import java.util.function.IntSupplier;

public class IncrementalIntSupplier implements IntSupplier {

    private int step;
    private int acc = 0;

    public IncrementalIntSupplier(int step) {
        this.step = step;
    }

    @Override
    public int getAsInt() {
        acc += step;
        return acc;
    }
}
