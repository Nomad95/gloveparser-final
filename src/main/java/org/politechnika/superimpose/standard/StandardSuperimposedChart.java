package org.politechnika.superimpose.standard;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.Optimized;
import org.politechnika.data_parser.model.TimeSequential;
import org.politechnika.model.glove.GloveValueDto;
import org.politechnika.model.kinect.PointDistanceValueDto;
import org.politechnika.model.pulsometer.PulsometerValueDto;
import org.politechnika.superimpose.Projection;
import org.politechnika.superimpose.Superimposed;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.politechnika.superimpose.standard.SeriesType.PULS;

/**
 * Pulsometer has precedence in this series (if exists)
 */
@SuppressWarnings("Duplicates")
@Slf4j
@AllArgsConstructor
@Optimized(optimizationFactor = 9000)
class StandardSuperimposedChart implements Superimposed {

    private final TimeFrequencyAnalyzer timeFrequencyAnalyzer;
    private final SeriesTransformer seriesTransformer;
    private Projection projection;

    StandardSuperimposedChart(TimeFrequencyAnalyzer timeFrequencyAnalyzer, SeriesTransformer seriesTransformer, Projection projection) {
        this.timeFrequencyAnalyzer = timeFrequencyAnalyzer;
        this.seriesTransformer = seriesTransformer;
        this.projection = projection;
    }

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
        this.projection = projection;
    }

    @Override
    public void adjustSeries() {
        SeriesType mostFrequentSeries = timeFrequencyAnalyzer.findSeriesWithMostFrequentChanges(this);
        if (projection.cutPulsometer())
            seriesTransformer.cutPulsometerValues(this, val -> val == 0);
        if (projection.startAtSameTime())
            seriesTransformer.transformSeriesToStartAtSameTimeAs(this, mostFrequentSeries);
        if (projection.endAtSameTime())
            seriesTransformer.transformSeriesToEndAtSameTimeAs(this, mostFrequentSeries);
        if (projection.cutOtherToAlignToPulsometer())
            seriesTransformer.cutTimeOfOtherSeriesToAlignToSeriesOfType(this, PULS);
        if (projection.cleanData())
            seriesTransformer.cleanData(this);
        seriesTransformer.interpolateWithSeries(this, mostFrequentSeries);
    }

    @Override
    public SuperimposedChartBundle getChartBundle() {
        //TODO: wydzielic
        Instant first = Instant.now();
        if (!pulsometerValues.isEmpty()) {
            first = pulsometerValues.get(0).getTime();
        }

        if (!leftGloveValues.isEmpty()) {
            if (leftGloveValues.get(0).getTime().isBefore(first)) {
                first = leftGloveValues.get(0).getTime();
            }
        }

        if (!rightGloveValues.isEmpty()) {
            if (rightGloveValues.get(0).getTime().isBefore(first)) {
                first = rightGloveValues.get(0).getTime();
            }
        }

        if (!kinectValues.isEmpty()) {
            if (kinectValues.get(0).getTime().isBefore(first)) {
                first = kinectValues.get(0).getTime();
            }
        }

        long startMilli = first.toEpochMilli();

        SuperimposedChartBundle bundle = new SuperimposedChartBundle();
        if (!pulsometerValues.isEmpty()) {
            double[] timeSeries = pulsometerStream().mapToDouble(v -> getTimeSeconds(startMilli, v)).toArray();
            double[] values = pulsometerStream().mapToDouble(PulsometerValueDto::getValue).toArray();
            bundle.addPulsometerSeries(new StandardDataSeries(timeSeries, new Object[]{values}));
        }

        if (!leftGloveValues.isEmpty()) {
            double[] timeSeries = leftGloveStream().mapToDouble(v -> getTimeSeconds(startMilli, v)).toArray();
            double[] thumbSeries = leftGloveStream().mapToDouble(GloveValueDto::getThumb).toArray();
            double[] indexSeries = leftGloveStream().mapToDouble(GloveValueDto::getIndex).toArray();
            double[] middleSeries = leftGloveStream().mapToDouble(GloveValueDto::getMiddle).toArray();
            double[] ringSeries = leftGloveStream().mapToDouble(GloveValueDto::getRing).toArray();
            double[] littleSeries = leftGloveStream().mapToDouble(GloveValueDto::getLittle).toArray();
            double[] avg = new double[timeSeries.length];
            for (int i = 0; i < avg.length; i++) {
                avg[i] = (thumbSeries[i] + indexSeries[i] + middleSeries[i] + ringSeries[i] + littleSeries[i] )/ 5;
            }
            bundle.addGloveSeries(new StandardDataSeries(timeSeries, new Object[]{avg}));
        }

        if (!rightGloveValues.isEmpty()) {
            double[] timeSeries = rightGloveStream().mapToDouble(v -> getTimeSeconds(startMilli, v)).toArray();
            double[] thumbSeries = rightGloveStream().mapToDouble(GloveValueDto::getThumb).toArray();
            double[] indexSeries = rightGloveStream().mapToDouble(GloveValueDto::getIndex).toArray();
            double[] middleSeries = rightGloveStream().mapToDouble(GloveValueDto::getMiddle).toArray();
            double[] ringSeries = rightGloveStream().mapToDouble(GloveValueDto::getRing).toArray();
            double[] littleSeries = rightGloveStream().mapToDouble(GloveValueDto::getLittle).toArray();
            double[] avg = new double[timeSeries.length];
            for (int i = 0; i < avg.length; i++) {
                avg[i] = (thumbSeries[i] + indexSeries[i] + middleSeries[i] + ringSeries[i] + littleSeries[i] )/ 5;
            }
            bundle.addGloveSeries(new StandardDataSeries(timeSeries, new Object[]{avg}));
        }

        if (!kinectValues.isEmpty()) {
            double[] timeSeries = kinectStream().mapToDouble(v -> getTimeSeconds(startMilli, v)).toArray();
            double[] head = kinectStream().mapToDouble(v -> v.getHead()).toArray();
            double[] neck = kinectStream().mapToDouble(v -> v.getNeck()).toArray();
            double[] spineBase = kinectStream().mapToDouble(v -> v.getSpineBase()).toArray();
            double[] spineMid = kinectStream().mapToDouble(v -> v.getSpineMid()).toArray();
            double[] spineShoulder = kinectStream().mapToDouble(v -> v.getSpineShoulder()).toArray();
            double[] avg = new double[timeSeries.length];
            for (int i = 0; i < avg.length; i++) {
                avg[i] = (head[i] + neck[i] + spineBase[i] + spineMid[i] + spineShoulder[i] )/ 5;
            }
            bundle.addKinectSeries(new StandardDataSeries(timeSeries, new Object[]{avg}));

            double[] lshoulder = kinectStream().mapToDouble(v -> v.getShoulderLeft()).toArray();
            double[] lelbow = kinectStream().mapToDouble(v -> v.getElbowLeft()).toArray();
            double[] lhand = kinectStream().mapToDouble(v -> v.getHandLeft()).toArray();
            double[] lhandTip = kinectStream().mapToDouble(v -> v.getHandTipLeft()).toArray();
            double[] lthumbLeft = kinectStream().mapToDouble(v -> v.getThumbLeft()).toArray();
            for (int i = 0; i < avg.length; i++) {
                avg[i] = (lshoulder[i] + lelbow[i] + lhand[i] + lhandTip[i] + lthumbLeft[i] )/ 5;
            }
            bundle.addKinectSeries(new StandardDataSeries(timeSeries, new Object[]{avg}));

            double[] rshoulder = kinectStream().mapToDouble(v -> v.getShoulderRight()).toArray();
            double[] relbow = kinectStream().mapToDouble(v -> v.getElbowRight()).toArray();
            double[] rhand = kinectStream().mapToDouble(v -> v.getHandRight()).toArray();
            double[] rhandTip = kinectStream().mapToDouble(v -> v.getHandTipRight()).toArray();
            double[] rthumbLeft = kinectStream().mapToDouble(v -> v.getThumbRight()).toArray();
            for (int i = 0; i < avg.length; i++) {
                avg[i] = (rshoulder[i] + relbow[i] + rhand[i] + rhandTip[i] + rthumbLeft[i] )/ 5;
            }
            bundle.addKinectSeries(new StandardDataSeries(timeSeries, new Object[]{avg}));

            double[] lhip = kinectStream().mapToDouble(v -> v.getHipLeft()).toArray();
            double[] lknee = kinectStream().mapToDouble(v -> v.getKneeLeft()).toArray();
            double[] lfoot = kinectStream().mapToDouble(v -> v.getFootLeft()).toArray();
            double[] lwrist = kinectStream().mapToDouble(v -> v.getWristLeft()).toArray();
            double[] lankle = kinectStream().mapToDouble(v -> v.getAnkleLeft()).toArray();
            for (int i = 0; i < avg.length; i++) {
                avg[i] = (lhip[i] + lknee[i] + lfoot[i] + lwrist[i] + lankle[i] )/ 5;
            }
            bundle.addKinectSeries(new StandardDataSeries(timeSeries, new Object[]{avg}));

            double[] rhip = kinectStream().mapToDouble(v -> v.getHipRight()).toArray();
            double[] rknee = kinectStream().mapToDouble(v -> v.getKneeRight()).toArray();
            double[] rfoot = kinectStream().mapToDouble(v -> v.getFootRight()).toArray();
            double[] rwrist = kinectStream().mapToDouble(v -> v.getWristRight()).toArray();
            double[] rankle = kinectStream().mapToDouble(v -> v.getAnkleRight()).toArray();
            for (int i = 0; i < avg.length; i++) {
                avg[i] = (rhip[i] + rknee[i] + rfoot[i] + rwrist[i] + rankle[i] )/ 5;
            }
            bundle.addKinectSeries(new StandardDataSeries(timeSeries, new Object[]{avg}));
        }

        return bundle;
    }

    private double getTimeSeconds(long startMili, TimeSequential sequential) {
        return ((double)sequential.getTime().toEpochMilli() - startMili) / 1000;
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
