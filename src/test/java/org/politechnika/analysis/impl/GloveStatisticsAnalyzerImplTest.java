package org.politechnika.analysis.impl;

import org.junit.Assert;
import org.junit.Test;
import org.politechnika.analysis.utils.ChartData;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class GloveStatisticsAnalyzerImplTest {

    private static final double DELTA = 0.001;

    GloveStatisticsAnalyzerImpl gloveStatisticsAnalyzer = new GloveStatisticsAnalyzerImpl();

    @Test
    public void calculateAverageForLeftHandEvery1sec() {
        Assert.assertEquals(getExcpectedAverageEvery1sec(), gloveStatisticsAnalyzer.averageOneFingerEvery1secFromHand(someExampleData(), "left"));
    }

    @Test
    public void calculateAllAverageForLeftHand() {
        Assert.assertEquals(getExcpectedAllAverage(), gloveStatisticsAnalyzer.calculateAverageDataFromFingers(getExcpectedAverageEvery1sec()));
    }

    @Test
    public void calculateVarianceForLeftHandEvery1sec() {
        Assert.assertEquals(getExcpectedVarianceEvery1sec(),
                gloveStatisticsAnalyzer.varianceOneFingerEvery1secFromHand(someExampleData(),getExcpectedAverageEvery1sec(), "left"));
    }

    @Test
    public void calculateAllVarianceForLeftHand() {
        Assert.assertEquals(getExcpectedAllVariance(), gloveStatisticsAnalyzer.calculateAverageDataFromFingers(getExcpectedVarianceEvery1sec()));
    }

    private List<GloveDataDto> someExampleData() {
        return Arrays.asList(
                new GloveDataDto("left",0,0.5,2847,2832,2868,Instant.parse("2018-06-10T11:48:34.7705Z")),
                new GloveDataDto("left",0,0.6,2848,2833,2869,Instant.parse("2018-06-10T11:48:34.7709Z")),
                new GloveDataDto("left",1,0.7,2849,2834,2870,Instant.parse("2018-06-10T11:48:34.7711Z")),
                new GloveDataDto("right",0,0.5,2847,2832,2868,Instant.parse("2018-06-10T11:48:34.7705Z")),
                new GloveDataDto("right",0,0.6,2848,2833,2869,Instant.parse("2018-06-10T11:48:34.7709Z")),
                new GloveDataDto("right",0,0.7,2849,2834,2870,Instant.parse("2018-06-10T11:48:34.7711Z")),
                new GloveDataDto("left",12,0.5,2847,2832,2868,Instant.parse("2018-06-10T11:48:34.7705Z")),
                new GloveDataDto("left",13,0.6,2848,2833,2869,Instant.parse("2018-06-10T11:48:34.7709Z")),
                new GloveDataDto("left",13,0.7,2849,2834,2870,Instant.parse("2018-06-10T11:48:34.7711Z")),
                new GloveDataDto("left",0,0.416,2848,2832,2868,Instant.parse("2018-06-10T11:48:35.7705Z")));
    }

    private ChartData getExcpectedAverageEvery1sec() {
        ChartData data = new ChartData(2);
        data.setTimeElem(0, 0);
        data.setFinger1Elem(0, 2848);
        data.setFinger5Elem(0, 2848);
        data.setTimeElem(1, 1);
        data.setFinger1Elem(1, 2848);
        return data;
    }

    private ChartData getExcpectedAllAverage() {
        ChartData data = new ChartData(1);
        data.setTimeElem(0, 0);
        data.setFinger1Elem(0, 2848);
        data.setFinger5Elem(0, 1424);
        return data;
    }

    private ChartData getExcpectedVarianceEvery1sec() {
        ChartData data = new ChartData(2);
        data.setTimeElem(0, 0);
        data.setFinger1Elem(0, 0.6666666666666666);
        data.setFinger5Elem(0, 0.6666666666666666);
        data.setTimeElem(1, 1);
        data.setFinger1Elem(1, 0d);
        return data;
    }

    private ChartData getExcpectedAllVariance() {
        ChartData data = new ChartData(1);
        data.setTimeElem(0, 0);
        data.setFinger1Elem(0, 0.3333333333333333);
        data.setFinger5Elem(0, 0.3333333333333333);
        return data;
    }
}