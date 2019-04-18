package org.politechnika.report.kinect_functions;

import org.politechnika.data_parser.model.KinectDataDto;
import org.politechnika.model.kinect.PointDistance;

import static org.politechnika.model.kinect.Sensor.*;

class PointVectorLengthCalculator {

    void calculateVectorLength(PointDistance point, KinectDataDto former, KinectDataDto latter) {
        point.setValueFor(SPINE_BASE, Math.sqrt(
                Math.pow(latter.getSpineBase_x() - former.getSpineBase_x(), 2) +
                        Math.pow(latter.getSpineBase_y() - former.getSpineBase_y(), 2) +
                        Math.pow(latter.getSpineBase_z() - former.getSpineBase_z(), 2)));
        point.setValueFor(SPINE_MID, Math.sqrt(
                Math.pow(latter.getSpineMid_x() - former.getSpineMid_x(), 2) +
                        Math.pow(latter.getSpineMid_y() - former.getSpineMid_y(), 2) +
                        Math.pow(latter.getSpineMid_z() - former.getSpineMid_z(), 2)));
        point.setValueFor(NECK, Math.sqrt(
                Math.pow(latter.getNeck_x() - former.getNeck_x(), 2) +
                        Math.pow(latter.getNeck_y() - former.getNeck_y(), 2) +
                        Math.pow(latter.getNeck_z() - former.getNeck_z(), 2)));
        point.setValueFor(HEAD, Math.sqrt(
                Math.pow(latter.getHead_x() - former.getHead_x(), 2) +
                        Math.pow(latter.getHead_y() - former.getHead_y(), 2) +
                        Math.pow(latter.getHead_z() - former.getHead_z(), 2)));
        point.setValueFor(SHOULDER_LEFT, Math.sqrt(
                Math.pow(latter.getShoulderLeft_x() - former.getShoulderLeft_x(), 2) +
                        Math.pow(latter.getShoulderLeft_y() - former.getShoulderLeft_y(), 2) +
                        Math.pow(latter.getShoulderLeft_z() - former.getShoulderLeft_z(), 2)));
        point.setValueFor(SHOULDER_RIGHT, Math.sqrt(
                Math.pow(latter.getShoulderRight_x() - former.getShoulderRight_x(), 2) +
                        Math.pow(latter.getShoulderRight_y() - former.getShoulderRight_y(), 2) +
                        Math.pow(latter.getShoulderRight_z() - former.getShoulderRight_z(), 2)));
        point.setValueFor(ELBOW_LEFT, Math.sqrt(
                Math.pow(latter.getElbowLeft_x() - former.getElbowLeft_x(), 2) +
                        Math.pow(latter.getElbowLeft_y() - former.getElbowLeft_y(), 2) +
                        Math.pow(latter.getElbowLeft_z() - former.getElbowLeft_z(), 2)));
        point.setValueFor(ELBOW_RIGHT, Math.sqrt(
                Math.pow(latter.getElbowRight_x() - former.getElbowRight_x(), 2) +
                        Math.pow(latter.getElbowRight_y() - former.getElbowRight_y(), 2) +
                        Math.pow(latter.getElbowRight_z() - former.getElbowRight_z(), 2)));
        point.setValueFor(WRIST_LEFT, Math.sqrt(
                Math.pow(latter.getWristLeft_x() - former.getWristLeft_x(), 2) +
                        Math.pow(latter.getWristLeft_y() - former.getWristLeft_y(), 2) +
                        Math.pow(latter.getWristLeft_z() - former.getWristLeft_z(), 2)));
        point.setValueFor(WRIST_RIGHT, Math.sqrt(
                Math.pow(latter.getWristRight_x() - former.getWristRight_x(), 2) +
                        Math.pow(latter.getWristRight_y() - former.getWristRight_y(), 2) +
                        Math.pow(latter.getWristRight_z() - former.getWristRight_z(), 2)));
        point.setValueFor(HAND_LEFT, Math.sqrt(
                Math.pow(latter.getHandLeft_x() - former.getHandLeft_x(), 2) +
                        Math.pow(latter.getHandLeft_y() - former.getHandLeft_y(), 2) +
                        Math.pow(latter.getHandLeft_z() - former.getHandLeft_z(), 2)));
        point.setValueFor(HAND_RIGHT, Math.sqrt(
                Math.pow(latter.getHandRight_x() - former.getHandRight_x(), 2) +
                        Math.pow(latter.getHandRight_y() - former.getHandRight_y(), 2) +
                        Math.pow(latter.getHandRight_z() - former.getHandRight_z(), 2)));
        point.setValueFor(HIP_LEFT, Math.sqrt(
                Math.pow(latter.getHipLeft_x() - former.getHipLeft_x(), 2) +
                        Math.pow(latter.getHipLeft_y() - former.getHandLeft_y(), 2) +
                        Math.pow(latter.getHipLeft_z() - former.getHipLeft_z(), 2)));
        point.setValueFor(HIP_RIGHT, Math.sqrt(
                Math.pow(latter.getHipRight_x() - former.getHipRight_x(), 2) +
                        Math.pow(latter.getHipRight_y() - former.getHandRight_y(), 2) +
                        Math.pow(latter.getHipRight_z() - former.getHipRight_z(), 2)));
        point.setValueFor(KNEE_LEFT, Math.sqrt(
                Math.pow(latter.getKneeLeft_x() - former.getKneeLeft_x(), 2) +
                        Math.pow(latter.getKneeLeft_y() - former.getKneeLeft_y(), 2) +
                        Math.pow(latter.getKneeLeft_z() - former.getKneeLeft_z(), 2)));
        point.setValueFor(KNEE_RIGHT, Math.sqrt(
                Math.pow(latter.getKneeRight_x() - former.getKneeRight_x(), 2) +
                        Math.pow(latter.getKneeRight_y() - former.getKneeRight_y(), 2) +
                        Math.pow(latter.getKneeRight_z() - former.getKneeRight_z(), 2)));
        point.setValueFor(ANKLE_LEFT, Math.sqrt(
                Math.pow(latter.getAnkleLeft_x() - former.getAnkleLeft_x(), 2) +
                        Math.pow(latter.getAnkleLeft_y() - former.getAnkleLeft_y(), 2) +
                        Math.pow(latter.getAnkleLeft_z() - former.getAnkleLeft_z(), 2)));
        point.setValueFor(ANKLE_RIGHT, Math.sqrt(
                Math.pow(latter.getAnkleRight_x() - former.getAnkleRight_x(), 2) +
                        Math.pow(latter.getAnkleRight_y() - former.getAnkleRight_y(), 2) +
                        Math.pow(latter.getAnkleRight_z() - former.getAnkleRight_z(), 2)));
        point.setValueFor(FOOT_LEFT, Math.sqrt(
                Math.pow(latter.getFootLeft_x() - former.getFootLeft_x(), 2) +
                        Math.pow(latter.getFootLeft_y() - former.getFootLeft_y(), 2) +
                        Math.pow(latter.getFootLeft_z() - former.getFootLeft_z(), 2)));
        point.setValueFor(FOOT_RIGHT, Math.sqrt(
                Math.pow(latter.getFootRight_x() - former.getFootRight_x(), 2) +
                        Math.pow(latter.getFootRight_y() - former.getFootRight_y(), 2) +
                        Math.pow(latter.getFootRight_z() - former.getFootRight_z(), 2)));
        point.setValueFor(SPINE_SHOULDER, Math.sqrt(
                Math.pow(latter.getSpineShoulder_x() - former.getSpineShoulder_x(), 2) +
                        Math.pow(latter.getSpineShoulder_y() - former.getSpineShoulder_y(), 2) +
                        Math.pow(latter.getSpineShoulder_z() - former.getSpineShoulder_z(), 2)));
        point.setValueFor(HAND_TIP_LEFT, Math.sqrt(
                Math.pow(latter.getHandTipLeft_x() - former.getHandTipLeft_x(), 2) +
                        Math.pow(latter.getHandTipLeft_y() - former.getHandTipLeft_y(), 2) +
                        Math.pow(latter.getHandTipLeft_z() - former.getHandTipLeft_z(), 2)));
        point.setValueFor(HAND_TIP_RIGHT, Math.sqrt(
                Math.pow(latter.getHandTipRight_x() - former.getHandTipRight_x(), 2) +
                        Math.pow(latter.getHandTipRight_y() - former.getHandTipRight_y(), 2) +
                        Math.pow(latter.getHandTipRight_z() - former.getHandTipRight_z(), 2)));
        point.setValueFor(THUMB_LEFT, Math.sqrt(
                Math.pow(latter.getThumbLeft_x() - former.getThumbLeft_x(), 2) +
                        Math.pow(latter.getThumbLeft_y() - former.getThumbLeft_y(), 2) +
                        Math.pow(latter.getThumbLeft_z() - former.getThumbLeft_z(), 2)));
        point.setValueFor(THUMB_RIGHT, Math.sqrt(
                Math.pow(latter.getThumbRight_x() - former.getThumbRight_x(), 2) +
                        Math.pow(latter.getThumbRight_y() - former.getThumbRight_y(), 2) +
                        Math.pow(latter.getThumbRight_z() - former.getThumbRight_z(), 2)));
    }
}
