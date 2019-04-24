package org.politechnika.superimpose.standard;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.Optimized;
import org.politechnika.data_parser.model.TimeSequential;
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
        seriesTransformer.cutPulsometerValues(this, val -> val == 0);
        seriesTransformer.transformSeriesToStartAtSameTimeAs(this, mostFrequentSeries);
        seriesTransformer.cutTimeOfOtherSeriesToAlignToSeriesOfType(this, PULS);
        seriesTransformer.interpolateWithSeries(this, mostFrequentSeries);
    }

    @Override
    public LineChart<Number, Number> getChart() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Czas");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Wartość");
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Nałożone wykresy");

        XYChart.Series<Number, Number> head = new XYChart.Series<>();
        XYChart.Series<Number, Number> neck = new XYChart.Series<>();
        XYChart.Series<Number, Number> spineShoulder = new XYChart.Series<>();
        XYChart.Series<Number, Number> spineMid = new XYChart.Series<>();
        XYChart.Series<Number, Number> spineBase = new XYChart.Series<>();

        XYChart.Series<Number, Number> shoulderLeft = new XYChart.Series<>();
        XYChart.Series<Number, Number> elbowLeft = new XYChart.Series<>();
        XYChart.Series<Number, Number> handLeft = new XYChart.Series<>();
        XYChart.Series<Number, Number> handTipLeft = new XYChart.Series<>();
        XYChart.Series<Number, Number> thumbLeft = new XYChart.Series<>();

        XYChart.Series<Number, Number> shoulderRight = new XYChart.Series<>();
        XYChart.Series<Number, Number> elbowRight = new XYChart.Series<>();
        XYChart.Series<Number, Number> handRight = new XYChart.Series<>();
        XYChart.Series<Number, Number> handTipRight = new XYChart.Series<>();
        XYChart.Series<Number, Number> thumbRight = new XYChart.Series<>();

        XYChart.Series<Number, Number> wristLeft = new XYChart.Series<>();
        XYChart.Series<Number, Number> hipLeft = new XYChart.Series<>();
        XYChart.Series<Number, Number> kneeLeft = new XYChart.Series<>();
        XYChart.Series<Number, Number> ankleLeft = new XYChart.Series<>();
        XYChart.Series<Number, Number> footLeft = new XYChart.Series<>();

        XYChart.Series<Number, Number> wristRight = new XYChart.Series<>();
        XYChart.Series<Number, Number> hipRight = new XYChart.Series<>();
        XYChart.Series<Number, Number> kneeRight = new XYChart.Series<>();
        XYChart.Series<Number, Number> ankleRight = new XYChart.Series<>();
        XYChart.Series<Number, Number> footRight = new XYChart.Series<>();

        long startMili = kinectValues.get(0).getTime().toEpochMilli();
        for (PointDistanceValueDto kinectValue : kinectValues) {
            head.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getHead()));
            neck.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getNeck()));
            spineShoulder.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getSpineShoulder()));
            spineMid.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getSpineMid()));
            spineBase.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getSpineBase()));

            shoulderLeft.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getShoulderLeft()));
            elbowLeft.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getElbowLeft()));
            handLeft.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getHandLeft()));
            handTipLeft.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getHandTipLeft()));
            thumbLeft.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getThumbLeft()));

            shoulderRight.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getShoulderRight()));
            elbowRight.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getElbowRight()));
            handRight.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getHandRight()));
            handTipRight.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getHandTipRight()));
            thumbRight.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getThumbRight()));

            wristLeft.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getWristLeft()));
            hipLeft.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getHipLeft()));
            kneeLeft.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getKneeLeft()));
            ankleLeft.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getAnkleLeft()));
            footLeft.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getFootLeft()));

            wristRight.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getWristRight()));
            hipRight.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getHipRight()));
            kneeRight.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getKneeRight()));
            ankleRight.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getAnkleRight()));
            footRight.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, kinectValue), kinectValue.getFootRight()));
        }

        lineChart.getData().addAll(head, neck, spineShoulder, spineMid, spineBase);
        head.nodeProperty().get().setStyle("-fx-stroke: red;");
        neck.nodeProperty().get().setStyle("-fx-stroke: red;");
        spineShoulder.nodeProperty().get().setStyle("-fx-stroke: red;");
        spineMid.nodeProperty().get().setStyle("-fx-stroke: red;");
        spineBase.nodeProperty().get().setStyle("-fx-stroke: red;");

        lineChart.getData().addAll(shoulderLeft, elbowLeft, handLeft, handTipLeft, thumbLeft);
        shoulderLeft.nodeProperty().get().setStyle("-fx-stroke: #9f0000;");
        elbowLeft.nodeProperty().get().setStyle("-fx-stroke: #9f0000;");
        handLeft.nodeProperty().get().setStyle("-fx-stroke: #9f0000;");
        handTipLeft.nodeProperty().get().setStyle("-fx-stroke: #9f0000;");
        thumbLeft.nodeProperty().get().setStyle("-fx-stroke: #9f0000;");

        lineChart.getData().addAll(shoulderRight, elbowRight, handRight, handTipRight, thumbRight);
        shoulderRight.nodeProperty().get().setStyle("-fx-stroke: #850000;");
        elbowRight.nodeProperty().get().setStyle("-fx-stroke: #850000;");
        handRight.nodeProperty().get().setStyle("-fx-stroke: #850000;");
        handTipRight.nodeProperty().get().setStyle("-fx-stroke: #850000;");
        thumbRight.nodeProperty().get().setStyle("-fx-stroke: #850000;");

        lineChart.getData().addAll(wristLeft, hipLeft, kneeLeft, ankleLeft, footLeft);
        wristLeft.nodeProperty().get().setStyle("-fx-stroke: #4f0000;");
        hipLeft.nodeProperty().get().setStyle("-fx-stroke: #4f0000;");
        kneeLeft.nodeProperty().get().setStyle("-fx-stroke: #4f0000;");
        ankleLeft.nodeProperty().get().setStyle("-fx-stroke: #4f0000;");
        footLeft.nodeProperty().get().setStyle("-fx-stroke: #4f0000;");

        lineChart.getData().addAll(wristRight, hipRight, kneeRight, ankleRight, footRight);
        wristRight.nodeProperty().get().setStyle("-fx-stroke: #3a0000;");
        hipRight.nodeProperty().get().setStyle("-fx-stroke: #3a0000;");
        kneeRight.nodeProperty().get().setStyle("-fx-stroke: #3a0000;");
        ankleRight.nodeProperty().get().setStyle("-fx-stroke: #3a0000;");
        footRight.nodeProperty().get().setStyle("-fx-stroke: #3a0000;");

        XYChart.Series<Number, Number> puls = new XYChart.Series<>();
        startMili = kinectValues.get(0).getTime().toEpochMilli();
        for (PulsometerValueDto pulsometerValue : pulsometerValues) {
            puls.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, pulsometerValue), pulsometerValue.getValue()));
        }

        lineChart.getData().addAll(puls);
        puls.nodeProperty().get().setStyle("-fx-stroke: #367aff;");

        XYChart.Series<Number, Number> lThumb = new XYChart.Series<>();
        XYChart.Series<Number, Number> lIndex = new XYChart.Series<>();
        XYChart.Series<Number, Number> lMiddle = new XYChart.Series<>();
        XYChart.Series<Number, Number> lRing = new XYChart.Series<>();
        XYChart.Series<Number, Number> lLitle = new XYChart.Series<>();

        startMili = kinectValues.get(0).getTime().toEpochMilli();
        for (GloveValueDto leftGloveValue : leftGloveValues) {
            lThumb.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, leftGloveValue), leftGloveValue.getThumb()));
            lIndex.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, leftGloveValue), leftGloveValue.getIndex()));
            lMiddle.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, leftGloveValue), leftGloveValue.getMiddle()));
            lRing.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, leftGloveValue), leftGloveValue.getRing()));
            lLitle.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, leftGloveValue), leftGloveValue.getLittle()));
        }

        lineChart.getData().addAll(lThumb, lIndex, lMiddle, lRing, lLitle);
        lThumb.nodeProperty().get().setStyle("-fx-stroke: #149743;");
        lIndex.nodeProperty().get().setStyle("-fx-stroke: #149743;");
        lMiddle.nodeProperty().get().setStyle("-fx-stroke: #149743;");
        lRing.nodeProperty().get().setStyle("-fx-stroke: #149743;");
        lLitle.nodeProperty().get().setStyle("-fx-stroke: #149743;");

        XYChart.Series<Number, Number> rThumb = new XYChart.Series<>();
        XYChart.Series<Number, Number> rIndex = new XYChart.Series<>();
        XYChart.Series<Number, Number> rMiddle = new XYChart.Series<>();
        XYChart.Series<Number, Number> rRing = new XYChart.Series<>();
        XYChart.Series<Number, Number> rLitle = new XYChart.Series<>();

        startMili = kinectValues.get(0).getTime().toEpochMilli();
        for (GloveValueDto leftGloveValue : leftGloveValues) {
            rThumb.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, leftGloveValue), leftGloveValue.getThumb()));
            rIndex.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, leftGloveValue), leftGloveValue.getIndex()));
            rMiddle.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, leftGloveValue), leftGloveValue.getMiddle()));
            rRing.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, leftGloveValue), leftGloveValue.getRing()));
            rLitle.getData().add(new XYChart.Data<>(getTimeSeconds(startMili, leftGloveValue), leftGloveValue.getLittle()));
        }

        lineChart.getData().addAll(rThumb, rIndex, rMiddle, rRing, rLitle);
        rThumb.nodeProperty().get().setStyle("-fx-stroke: #127037;");
        rIndex.nodeProperty().get().setStyle("-fx-stroke: #127037;");
        rMiddle.nodeProperty().get().setStyle("-fx-stroke: #127037;");
        rRing.nodeProperty().get().setStyle("-fx-stroke: #127037;");
        rLitle.nodeProperty().get().setStyle("-fx-stroke: #127037;");

        return lineChart;
    }

    private double getTimeSeconds(long startMili, TimeSequential kinectValue) {
        return ((double)kinectValue.getTime().toEpochMilli() - startMili) / 1000;
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
