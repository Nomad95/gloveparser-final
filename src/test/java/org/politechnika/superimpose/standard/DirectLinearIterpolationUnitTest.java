package org.politechnika.superimpose.standard;

import org.junit.Assert;
import org.junit.Test;

public class DirectLinearIterpolationUnitTest {

    @Test
    public void shouldInterpolate() {
        DirectLinearInterpolation interp = new DirectLinearInterpolation();
        double result = interp.interpolate(15, 362.78, 20, 517.35, 16);

        Assert.assertEquals(393.7, result, 0.1);
    }
}
