package org.politechnika.superimpose.standard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.Optimized;
import org.politechnika.model.glove.GloveValueDto;
import org.politechnika.model.kinect.PointDistanceValueDto;
import org.politechnika.model.pulsometer.PulsometerValueDto;
import org.politechnika.superimpose.Projection;
import org.politechnika.superimpose.Superimposed;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.politechnika.superimpose.standard.SeriesType.PULS;

/**
 * Pulsometer has precedence in this series (if exists)
 */
@Slf4j
@RequiredArgsConstructor
@Optimized(optimizationFactor = 9000)
class StandardSuperimposedChart implements Superimposed {

    private final TimeFrequencyAnalyzer timeFrequencyAnalyzer;
    private final SeriesTransformer seriesTransformer;

    List<GloveValueDto> leftGloveValues = new LinkedList<>();
    List<GloveValueDto> rightGloveValues = new LinkedList<>();
    List<PointDistanceValueDto> kinectValues = new LinkedList<>();
    List<PulsometerValueDto> pulsometerValues = new LinkedList<>();

    @Override
    public void loadLeftGloveValues(List<GloveValueDto> values) {
        this.leftGloveValues = values;
    }

    @Override
    public void loadRightGloveValues(List<GloveValueDto> values) {
        this.rightGloveValues = values;
    }

    @Override
    public void loadKinectValues(List<PointDistanceValueDto> values) {
        this.kinectValues = values;
    }

    @Override
    public void loadPulsometerValues(List<PulsometerValueDto> values) {
        this.pulsometerValues = values;
    }

    @Override
    public void setProjection(Projection projection) {

    }

    @Override
    public void adjustSeries() {
        SeriesType mostFrequentSeries = timeFrequencyAnalyzer.findSeriesWithMostFrequentChanges(this);
        seriesTransformer.transformSeriesToStartAtSameTimeAs(this, mostFrequentSeries);
        seriesTransformer.cutPulsometerValues(this, val -> val == 0);
        seriesTransformer.cutTimeOfOtherSeriesToAlignToSeriesOfType(this, PULS);
        seriesTransformer.interpolateWithSeries(this, mostFrequentSeries);
    }

    Stream<GloveValueDto> leftGloveStream() {
        return leftGloveValues.stream();
    }

    Stream<GloveValueDto> rightGloveStream() {
        return rightGloveValues.stream();
    }

    Stream<PointDistanceValueDto> kinectStream() {
        return kinectValues.stream();
    }

    Stream<PulsometerValueDto> pulsometerStream() {
        return pulsometerValues.stream();
    }
}
