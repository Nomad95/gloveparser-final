package org.politechnika.processing;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.stream.DoubleStream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.politechnika.processing.DoubleArrayTimeSeries.AligningMode.LAST_VALUE;

public class DoubleArrayTimeSeriesUnitTest {

    @Test
    public void shouldAlignArrays() {
        //given
        DoubleArrayTimeSeries doubleArrayTimeSeries = new DoubleArrayTimeSeries();
        double[] shorterSeries = DoubleStream.iterate(0, n -> n + 1).limit(10).toArray();
        double[] oneValueLackSeries = DoubleStream.iterate(0, n -> n + 1).limit(19).toArray();
        double[] longerSeries = DoubleStream.iterate(0, n -> n + 1).limit(20).toArray();

        //when
        doubleArrayTimeSeries.addSeries("shorter", shorterSeries);
        doubleArrayTimeSeries.addSeries("longer", longerSeries);
        doubleArrayTimeSeries.addSeries("lack", oneValueLackSeries);
        doubleArrayTimeSeries.alignArrays(LAST_VALUE);

        //then
        Map<String, double[]> results = doubleArrayTimeSeries.getAllSeriesMap();
        Assert.assertThat(
                "Shorter array was not aligned to the longest array",
                results.get("shorter").length,
                is(equalTo(20)));
        Assert.assertThat(
                "Shorter array was not aligned to the longest array",
                results.get("lack").length,
                is(equalTo(20)));
        Assert.assertThat(
                results.get("longer").length,
                is(equalTo(20)));
    }
}
