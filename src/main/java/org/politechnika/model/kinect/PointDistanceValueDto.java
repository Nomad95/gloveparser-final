package org.politechnika.model.kinect;

import lombok.Value;
import org.politechnika.data_parser.model.DataDto;
import org.politechnika.data_parser.model.TimeSequential;

import java.time.Instant;

@Value
public class PointDistanceValueDto implements DataDto, TimeSequential {
    private double spineBase;
    private double spineMid;
    private double neck;
    private double head;
    private double shoulderLeft;
    private double elbowLeft;
    private double wristLeft;
    private double handLeft;
    private double shoulderRight;
    private double elbowRight;
    private double wristRight;
    private double handRight;
    private double hipLeft;
    private double kneeLeft;
    private double ankleLeft;
    private double footLeft;
    private double hipRight;
    private double kneeRight;
    private double ankleRight;
    private double footRight;
    private double spineShoulder;
    private double handTipLeft;
    private double thumbLeft;
    private double handTipRight;
    private double thumbRight;
    private Instant time;

    public PointDistanceValueDto moveInTime(Instant newTime) {
        return new PointDistanceValueDto(
                spineBase, spineMid, neck, head, shoulderLeft, elbowLeft,
                wristLeft, handLeft, shoulderRight, elbowRight, wristRight, handRight, hipLeft,
                kneeLeft, ankleLeft, footLeft, hipRight, kneeRight, ankleRight, footRight,
                spineShoulder, handTipLeft, thumbLeft, handTipRight, thumbRight, newTime);
    }
}
