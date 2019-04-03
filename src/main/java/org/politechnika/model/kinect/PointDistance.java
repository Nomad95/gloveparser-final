package org.politechnika.model.kinect;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.politechnika.data_parser.csv.definitions.DataDto;

@RequiredArgsConstructor
@Data
public class PointDistance implements DataDto {

    private final String description;

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

    public void setValueFor(Sensor sensor, double value) {
        switch (sensor) {
            case SPINE_BASE: spineBase = value; return;
            case SPINE_MID:  spineMid = value; return;
            case NECK:  neck  = value; return;
            case HEAD:  head  = value; return;
            case SHOULDER_LEFT:  shoulderLeft  = value; return;
            case SHOULDER_RIGHT:  shoulderRight  = value; return;
            case ELBOW_LEFT:  elbowLeft  = value; return;
            case ELBOW_RIGHT:  elbowRight  = value; return;
            case WRIST_LEFT:  wristLeft  = value; return;
            case WRIST_RIGHT:  wristRight  = value; return;
            case HAND_LEFT:  handLeft  = value; return;
            case HAND_RIGHT:  handRight  = value; return;
            case HIP_LEFT:  hipLeft  = value; return;
            case HIP_RIGHT:  hipRight  = value; return;
            case KNEE_LEFT:  kneeLeft  = value; return;
            case KNEE_RIGHT:  kneeRight  = value; return;
            case ANKLE_LEFT:  ankleLeft  = value; return;
            case ANKLE_RIGHT:  ankleRight  = value; return;
            case FOOT_LEFT:  footLeft  = value; return;
            case FOOT_RIGHT:  footRight  = value; return;
            case SPINE_SHOULDER:  spineShoulder  = value; return;
            case HAND_TIP_LEFT:  handTipLeft  = value; return;
            case HAND_TIP_RIGHT:  handTipRight  = value; return;
            case THUMB_LEFT:  thumbLeft  = value; return;
            case THUMB_RIGHT:  thumbRight  = value; return;
        }
    }

    public double getValueFor(Sensor sensor) {
        switch (sensor) {
            case SPINE_BASE: return spineBase;
            case SPINE_MID: return spineMid;
            case NECK: return neck;
            case HEAD: return head;
            case SHOULDER_LEFT: return shoulderLeft;
            case SHOULDER_RIGHT: return shoulderRight;
            case ELBOW_LEFT: return elbowLeft;
            case ELBOW_RIGHT: return elbowRight;
            case WRIST_LEFT: return wristLeft;
            case WRIST_RIGHT: return wristRight;
            case HAND_LEFT: return handLeft;
            case HAND_RIGHT: return handRight;
            case HIP_LEFT: return hipLeft;
            case HIP_RIGHT: return hipRight;
            case KNEE_LEFT: return kneeLeft;
            case KNEE_RIGHT: return kneeRight;
            case ANKLE_LEFT: return ankleLeft;
            case ANKLE_RIGHT: return ankleRight;
            case FOOT_LEFT: return footLeft;
            case FOOT_RIGHT: return footRight;
            case SPINE_SHOULDER: return spineShoulder;
            case HAND_TIP_LEFT: return handTipLeft;
            case HAND_TIP_RIGHT: return handTipRight;
            case THUMB_LEFT: return thumbLeft;
            case THUMB_RIGHT: return thumbRight;
        }
        throw new IllegalArgumentException("Could not find value for sensor " + sensor);
    }
}
