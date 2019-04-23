package org.politechnika.superimpose.standard;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealVector;
import org.politechnika.data_parser.model.TimeSequential;
import org.politechnika.model.glove.GloveValueDto;
import org.politechnika.model.kinect.PointDistanceValueDto;
import org.politechnika.model.pulsometer.PulsometerValueDto;

class DirectLinearInterpolation {

    double interpolate(long x1, double y1, long x2, double y2, long argument) {
        RealVector solution =
                new LUDecomposition(
                        new Array2DRowRealMatrix(new double[][]{{1, x1}, {1, x2}}, false))
                        .getSolver().solve(
                        new ArrayRealVector(new double[]{y1, y2}, false));

        return solution.getEntry(0) + (solution.getEntry(1) * argument);
    }

    PulsometerValueDto interpolatePuls(TimeSequential current, PulsometerValueDto next, PulsometerValueDto prev) {
        double interpolatedVal = interpolate(prev.getTime().toEpochMilli(), prev.getValue(),
                next.getTime().toEpochMilli(), next.getValue(),
                current.getTime().toEpochMilli());
         return new PulsometerValueDto(current.getTime(), interpolatedVal);
    }

    GloveValueDto interpolateHand(TimeSequential current, GloveValueDto next, GloveValueDto prev) {
        double thumb = interpolate(prev.getTime().toEpochMilli(), prev.getThumb(),
                next.getTime().toEpochMilli(), next.getThumb(),
                current.getTime().toEpochMilli());
        double index = interpolate(prev.getTime().toEpochMilli(), prev.getIndex(),
                next.getTime().toEpochMilli(), next.getIndex(),
                current.getTime().toEpochMilli());
        double middle = interpolate(prev.getTime().toEpochMilli(), prev.getMiddle(),
                next.getTime().toEpochMilli(), next.getMiddle(),
                current.getTime().toEpochMilli());
        double ring = interpolate(prev.getTime().toEpochMilli(), prev.getRing(),
                next.getTime().toEpochMilli(), next.getRing(),
                current.getTime().toEpochMilli());
        double little = interpolate(prev.getTime().toEpochMilli(), prev.getLittle(),
                next.getTime().toEpochMilli(), next.getLittle(),
                current.getTime().toEpochMilli());
        return new GloveValueDto(thumb, index, middle, ring, little, current.getTime());
    }

    PointDistanceValueDto interpolateKinect(TimeSequential current, PointDistanceValueDto next, PointDistanceValueDto prev) {
        double spineBase = interpolate(prev.getTime().toEpochMilli(), prev.getSpineBase(),
                next.getTime().toEpochMilli(), next.getSpineBase(),
                current.getTime().toEpochMilli());
        double spineMid = interpolate(prev.getTime().toEpochMilli(), prev.getSpineMid(),
                next.getTime().toEpochMilli(), next.getSpineMid(),
                current.getTime().toEpochMilli());
        double neck = interpolate(prev.getTime().toEpochMilli(), prev.getNeck(),
                next.getTime().toEpochMilli(), next.getNeck(),
                current.getTime().toEpochMilli());
        double head = interpolate(prev.getTime().toEpochMilli(), prev.getHead(),
                next.getTime().toEpochMilli(), next.getHead(),
                current.getTime().toEpochMilli());
        double shoulderLeft = interpolate(prev.getTime().toEpochMilli(), prev.getShoulderLeft(),
                next.getTime().toEpochMilli(), next.getShoulderLeft(),
                current.getTime().toEpochMilli());
        double elbowLeft = interpolate(prev.getTime().toEpochMilli(), prev.getElbowLeft(),
                next.getTime().toEpochMilli(), next.getElbowLeft(),
                current.getTime().toEpochMilli());
        double wristLeft = interpolate(prev.getTime().toEpochMilli(), prev.getWristLeft(),
                next.getTime().toEpochMilli(), next.getWristLeft(),
                current.getTime().toEpochMilli());
        double handLeft = interpolate(prev.getTime().toEpochMilli(), prev.getHandLeft(),
                next.getTime().toEpochMilli(), next.getHandLeft(),
                current.getTime().toEpochMilli());
        double shoulderRight = interpolate(prev.getTime().toEpochMilli(), prev.getShoulderRight(),
                next.getTime().toEpochMilli(), next.getShoulderRight(),
                current.getTime().toEpochMilli());
        double elbowRight = interpolate(prev.getTime().toEpochMilli(), prev.getElbowRight(),
                next.getTime().toEpochMilli(), next.getElbowRight(),
                current.getTime().toEpochMilli());
        double wristRight = interpolate(prev.getTime().toEpochMilli(), prev.getWristRight(),
                next.getTime().toEpochMilli(), next.getWristRight(),
                current.getTime().toEpochMilli());
        double handRight = interpolate(prev.getTime().toEpochMilli(), prev.getHandRight(),
                next.getTime().toEpochMilli(), next.getHandRight(),
                current.getTime().toEpochMilli());
        double hipLeft = interpolate(prev.getTime().toEpochMilli(), prev.getHipLeft(),
                next.getTime().toEpochMilli(), next.getHipLeft(),
                current.getTime().toEpochMilli());
        double kneeLeft = interpolate(prev.getTime().toEpochMilli(), prev.getKneeLeft(),
                next.getTime().toEpochMilli(), next.getKneeLeft(),
                current.getTime().toEpochMilli());
        double ankleLeft = interpolate(prev.getTime().toEpochMilli(), prev.getAnkleLeft(),
                next.getTime().toEpochMilli(), next.getAnkleLeft(),
                current.getTime().toEpochMilli());
        double footLeft = interpolate(prev.getTime().toEpochMilli(), prev.getFootLeft(),
                next.getTime().toEpochMilli(), next.getFootLeft(),
                current.getTime().toEpochMilli());
        double hipRight = interpolate(prev.getTime().toEpochMilli(), prev.getHipRight(),
                next.getTime().toEpochMilli(), next.getHipRight(),
                current.getTime().toEpochMilli());
        double kneeRight = interpolate(prev.getTime().toEpochMilli(), prev.getKneeRight(),
                next.getTime().toEpochMilli(), next.getKneeRight(),
                current.getTime().toEpochMilli());
        double ankleRight = interpolate(prev.getTime().toEpochMilli(), prev.getAnkleRight(),
                next.getTime().toEpochMilli(), next.getAnkleRight(),
                current.getTime().toEpochMilli());
        double footRight = interpolate(prev.getTime().toEpochMilli(), prev.getFootRight(),
                next.getTime().toEpochMilli(), next.getFootRight(),
                current.getTime().toEpochMilli());
        double spineShoulder = interpolate(prev.getTime().toEpochMilli(), prev.getSpineShoulder(),
                next.getTime().toEpochMilli(), next.getSpineShoulder(),
                current.getTime().toEpochMilli());
        double handTipLeft = interpolate(prev.getTime().toEpochMilli(), prev.getHandTipLeft(),
                next.getTime().toEpochMilli(), next.getHandTipLeft(),
                current.getTime().toEpochMilli());
        double thumbLeft = interpolate(prev.getTime().toEpochMilli(), prev.getThumbLeft(),
                next.getTime().toEpochMilli(), next.getThumbLeft(),
                current.getTime().toEpochMilli());
        double handTipRight = interpolate(prev.getTime().toEpochMilli(), prev.getHandTipRight(),
                next.getTime().toEpochMilli(), next.getHandTipRight(),
                current.getTime().toEpochMilli());
        double thumbRight = interpolate(prev.getTime().toEpochMilli(), prev.getThumbRight(),
                next.getTime().toEpochMilli(), next.getThumbRight(),
                current.getTime().toEpochMilli());
        return new PointDistanceValueDto(
                spineBase, spineMid, neck, head,
                shoulderLeft, elbowLeft, wristLeft, handLeft,
                shoulderRight, elbowRight, wristRight, handRight,
                hipLeft, kneeLeft, ankleLeft, footLeft,
                hipRight, kneeRight, ankleRight, footRight,
                spineShoulder,
                handTipLeft, thumbLeft,
                handTipRight, thumbRight,
                current.getTime()
        );
    }
}
