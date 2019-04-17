package org.politechnika.model.kinect;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class KinectStatisticsSummary {

    private final String description;

    private Kinect3DPoint spineBase = new Kinect3DPoint();

    private Kinect3DPoint spineMid = new Kinect3DPoint();

    private Kinect3DPoint neck = new Kinect3DPoint();

    private Kinect3DPoint head = new Kinect3DPoint();

    private Kinect3DPoint shoulderLeft = new Kinect3DPoint();

    private Kinect3DPoint elbowLeft = new Kinect3DPoint();

    private Kinect3DPoint wristLeft = new Kinect3DPoint();

    private Kinect3DPoint handLeft = new Kinect3DPoint();

    private Kinect3DPoint shoulderRight = new Kinect3DPoint();

    private Kinect3DPoint elbowRight = new Kinect3DPoint();

    private Kinect3DPoint wristRight = new Kinect3DPoint();

    private Kinect3DPoint handRight = new Kinect3DPoint();

    private Kinect3DPoint hipLeft = new Kinect3DPoint();

    private Kinect3DPoint kneeLeft = new Kinect3DPoint();

    private Kinect3DPoint ankleLeft = new Kinect3DPoint();

    private Kinect3DPoint footLeft = new Kinect3DPoint();

    private Kinect3DPoint hipRight = new Kinect3DPoint();

    private Kinect3DPoint kneeRight = new Kinect3DPoint();

    private Kinect3DPoint ankleRight = new Kinect3DPoint();

    private Kinect3DPoint footRight = new Kinect3DPoint();

    private Kinect3DPoint spineShoulder = new Kinect3DPoint();

    private Kinect3DPoint handTipLeft = new Kinect3DPoint();

    private Kinect3DPoint thumbLeft = new Kinect3DPoint();

    private Kinect3DPoint handTipRight = new Kinect3DPoint();

    private Kinect3DPoint thumbRight = new Kinect3DPoint();

    void setValueFor(Sensor sensor, Dimension dimension, double value) {
        switch (sensor) {
            case SPINE_BASE: setValueOnDimension(spineBase, dimension, value); return;
            case SPINE_MID: setValueOnDimension(spineMid, dimension, value); return;
            case NECK: setValueOnDimension(neck, dimension, value); return;
            case HEAD: setValueOnDimension(head, dimension, value); return;
            case SHOULDER_LEFT: setValueOnDimension(shoulderLeft, dimension, value); return;
            case SHOULDER_RIGHT: setValueOnDimension(shoulderRight, dimension, value); return;
            case ELBOW_LEFT: setValueOnDimension(elbowLeft, dimension, value); return;
            case ELBOW_RIGHT: setValueOnDimension(elbowRight, dimension, value); return;
            case WRIST_LEFT: setValueOnDimension(wristLeft, dimension, value); return;
            case WRIST_RIGHT: setValueOnDimension(wristRight, dimension, value); return;
            case HAND_LEFT: setValueOnDimension(handLeft, dimension, value); return;
            case HAND_RIGHT: setValueOnDimension(handRight, dimension, value); return;
            case HIP_LEFT: setValueOnDimension(hipLeft, dimension, value); return;
            case HIP_RIGHT: setValueOnDimension(hipRight, dimension, value); return;
            case KNEE_LEFT: setValueOnDimension(kneeLeft, dimension, value); return;
            case KNEE_RIGHT: setValueOnDimension(kneeRight, dimension, value); return;
            case ANKLE_LEFT: setValueOnDimension(ankleLeft, dimension, value); return;
            case ANKLE_RIGHT: setValueOnDimension(ankleRight, dimension, value); return;
            case FOOT_LEFT: setValueOnDimension(footLeft, dimension, value); return;
            case FOOT_RIGHT: setValueOnDimension(footRight, dimension, value); return;
            case SPINE_SHOULDER: setValueOnDimension(spineShoulder, dimension, value); return;
            case HAND_TIP_LEFT: setValueOnDimension(handTipLeft, dimension, value); return;
            case HAND_TIP_RIGHT: setValueOnDimension(handTipRight, dimension, value); return;
            case THUMB_LEFT: setValueOnDimension(thumbLeft, dimension, value); return;
            case THUMB_RIGHT: setValueOnDimension(thumbRight, dimension, value); return;
        }
    }

    public double getValueFor(Sensor sensor, Dimension dimension) {
        switch (sensor) {
            case SPINE_BASE: return getValueFromDimension(spineBase, dimension);
            case SPINE_MID: return getValueFromDimension(spineMid, dimension);
            case NECK: return getValueFromDimension(neck, dimension);
            case HEAD: return getValueFromDimension(head, dimension);
            case SHOULDER_LEFT: return getValueFromDimension(shoulderLeft, dimension);
            case SHOULDER_RIGHT: return getValueFromDimension(shoulderRight, dimension);
            case ELBOW_LEFT: return getValueFromDimension(elbowLeft, dimension);
            case ELBOW_RIGHT: return getValueFromDimension(elbowRight, dimension);
            case WRIST_LEFT: return getValueFromDimension(wristLeft, dimension);
            case WRIST_RIGHT: return getValueFromDimension(wristRight, dimension);
            case HAND_LEFT: return getValueFromDimension(handLeft, dimension);
            case HAND_RIGHT: return getValueFromDimension(handRight, dimension);
            case HIP_LEFT: return getValueFromDimension(hipLeft, dimension);
            case HIP_RIGHT: return getValueFromDimension(hipRight, dimension);
            case KNEE_LEFT: return getValueFromDimension(kneeLeft, dimension);
            case KNEE_RIGHT: return getValueFromDimension(kneeRight, dimension);
            case ANKLE_LEFT: return getValueFromDimension(ankleLeft, dimension);
            case ANKLE_RIGHT: return getValueFromDimension(ankleRight, dimension);
            case FOOT_LEFT: return getValueFromDimension(footLeft, dimension);
            case FOOT_RIGHT: return getValueFromDimension(footRight, dimension);
            case SPINE_SHOULDER: return getValueFromDimension(spineShoulder, dimension);
            case HAND_TIP_LEFT: return getValueFromDimension(handTipLeft, dimension);
            case HAND_TIP_RIGHT: return getValueFromDimension(handTipRight, dimension);
            case THUMB_LEFT: return getValueFromDimension(thumbLeft, dimension);
            case THUMB_RIGHT: return getValueFromDimension(thumbRight, dimension);
        }
        throw new IllegalArgumentException("Could not find value for sensor " + sensor);
    }

    private double getValueFromDimension(Kinect3DPoint field, Dimension dimension) {
        switch (dimension) {
            case X: return field.getX();
            case Y: return field.getY();
            case Z: return field.getZ();
        }
        throw new IllegalArgumentException("Could not find value for dimension " + dimension);
    }

    private void setValueOnDimension(Kinect3DPoint field, Dimension dimension, double value) {
        switch (dimension) {
            case X: field.setX(value); return;
            case Y: field.setY(value); return;
            case Z: field.setZ(value); return;
        }
    }

}
