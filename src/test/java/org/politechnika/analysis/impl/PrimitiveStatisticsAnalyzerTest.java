package org.politechnika.analysis.impl;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.politechnika.analysis.PrimitiveStatisticsAnalyzer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class PrimitiveStatisticsAnalyzerTest {

    @Test
    public void shouldComputeAverageOfIntArray() {
        PrimitiveStatisticsAnalyzer analyzer = new PrimitiveStatisticsAnalyzerImpl();
        int[] ints = {1, 5, 7};

        double result = analyzer.averageInt(ints);

        assertEquals(4.333, result, 0.001);
    }

    @Test
    public void shouldComputeAverageOfLargeIntValues() {
        PrimitiveStatisticsAnalyzer analyzer = new PrimitiveStatisticsAnalyzerImpl();
        int[] ints = {112312313, 512312313, 71231313};

        double result = analyzer.averageInt(ints);

        assertEquals(231951979.666, result, 0.001);
    }

    @Test
    public void shouldComputeAverageOfManyIntValues() {
        PrimitiveStatisticsAnalyzer analyzer = new PrimitiveStatisticsAnalyzerImpl();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int[] ints = random.ints(5000, 2000, 4000).toArray();

        double result = analyzer.averageInt(ints);

        Assert.assertTrue(result <= 4000 && result >= 2000);
    }

    @Test
    public void shouldReturnZeroOnEmptyIntArray() {
        PrimitiveStatisticsAnalyzer analyzer = new PrimitiveStatisticsAnalyzerImpl();

        double result = analyzer.averageInt(new int[0]);

        Assert.assertEquals(0, result, 0.001);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenIntArrayIsNull() {
        PrimitiveStatisticsAnalyzer analyzer = new PrimitiveStatisticsAnalyzerImpl();

        double result = analyzer.averageInt(null);

        Assert.assertEquals(0, result, 0.001);
    }

    @Test
    public void shouldComputeAverageOfDoubleArray() {
        PrimitiveStatisticsAnalyzer analyzer = new PrimitiveStatisticsAnalyzerImpl();
        double[] doubles = {1.0d, 5.4d, -7.8d};

        double result = analyzer.averageDouble(doubles);

        assertEquals(-0.4666, result, 0.001);
    }

    @Test
    public void shouldComputeAverageOfPrecisionDoubleValues() {
        PrimitiveStatisticsAnalyzer analyzer = new PrimitiveStatisticsAnalyzerImpl();
        double[] doubles = {1.9874627485, 0.756174624, 0.00047264};

        double result = analyzer.averageDouble(doubles);

        assertEquals(0.9147033375, result, 0.001);
    }

    @Test
    public void shouldComputeAverageOfManyDoubleValues() {
        PrimitiveStatisticsAnalyzer analyzer = new PrimitiveStatisticsAnalyzerImpl();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        double[] doubles = random.doubles(5000, 0.5, 0.6).toArray();

        double result = analyzer.averageDouble(doubles);

        Assert.assertTrue(result <= 0.6 && result >= 0.5);
    }

    @Test
    public void shouldReturnZeroOnEmptyDoubleArray() {
        PrimitiveStatisticsAnalyzer analyzer = new PrimitiveStatisticsAnalyzerImpl();

        double result = analyzer.averageDouble(new double[0]);

        Assert.assertEquals(0, result, 0.001);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNpeWhenDoubleArrayIsNull() {
        PrimitiveStatisticsAnalyzer analyzer = new PrimitiveStatisticsAnalyzerImpl();

        double result = analyzer.averageDouble(null);

        Assert.assertEquals(0, result, 0.001);
    }

    @Test
    public void shoulasdasd() {
        String[] strings = new String[]{"asd",null,null};
        List<String> list = Lists.newArrayList(strings);
        CollectionUtils.filter(list, PredicateUtils.notNullPredicate());
        list.forEach(System.out::println);
    }
}
