package org.politechnika.processing;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

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

    @Test
    public void shouldAdjustListSizeToSeriesLength() {
        //given
        DoubleArrayTimeSeries doubleArrayTimeSeries = new DoubleArrayTimeSeries();
        double[] series1 = DoubleStream.iterate(0, n -> n + 1).limit(20).toArray();
        double[] series2 = DoubleStream.iterate(0, n -> n + 1).limit(20).toArray();
        double[] series3 = DoubleStream.iterate(0, n -> n + 1).limit(20).toArray();
        doubleArrayTimeSeries.addSeries("1", series1);
        doubleArrayTimeSeries.addSeries("2", series2);
        doubleArrayTimeSeries.addSeries("3", series3);

        List<Instant> timestamps = IntStream.range(0, 17)
                .mapToObj(i -> Instant.now().plusSeconds(i))
                .collect(Collectors.toList());
        //when
        doubleArrayTimeSeries.adjustListToSeriesLength(timestamps);

        //then
        Assert.assertThat(timestamps.size(), is(equalTo(20)));
    }
}
