package org.politechnika.report.kinect_functions;

import org.politechnika.data_parser.BeanToCsvParser;
import org.politechnika.data_parser.CsvParsingException;
import org.politechnika.data_parser.model.KinectDataDto;
import org.politechnika.frontend.MainController;
import org.politechnika.model.kinect.PointDistance;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static org.politechnika.model.kinect.Sensor.*;

public class GenerateKinectPointDistanceReportCsv implements Function<List<KinectDataDto>, List<KinectDataDto>> {

    @Override
    public List<KinectDataDto> apply(List<KinectDataDto> kinectDataDtos) {
        List<PointDistance> points = new LinkedList<>();
        for (int i = 1 ; i < kinectDataDtos.size() - 1 ; i++) {
            points.add(calculatePoint(kinectDataDtos.get(i), kinectDataDtos.get(i-1), i));
        }

        //fixme
        try {
            new BeanToCsvParser().parseToCsv(points, MainController.getDestinationSubFolder() + "/kinect_odcinki_odleglosci.csv");
        } catch (CsvParsingException e) {
            e.printStackTrace();
        }

        return kinectDataDtos;
    }

    private PointDistance calculatePoint(KinectDataDto data, KinectDataDto data1, int i) {
        PointDistance pointDistance = new PointDistance("Point " + i);
        calculatePointDimension(pointDistance, data1, data);
        return pointDistance;
    }

    private void calculatePointDimension(PointDistance point, KinectDataDto data1, KinectDataDto data2) {
        point.setValueFor(SPINE_BASE, Math.sqrt(
                Math.pow(data2.getSpineBase_x() - data1.getSpineBase_x(), 2) +
                        Math.pow(data2.getSpineBase_y() - data1.getSpineBase_y(), 2) +
                        Math.pow(data2.getSpineBase_z() - data1.getSpineBase_z(), 2)));
        point.setValueFor(SPINE_MID, Math.sqrt(
                Math.pow(data2.getSpineMid_x() - data1.getSpineMid_x(), 2) +
                        Math.pow(data2.getSpineMid_y() - data1.getSpineMid_y(), 2) +
                        Math.pow(data2.getSpineMid_z() - data1.getSpineMid_z(), 2)));
        point.setValueFor(NECK, Math.sqrt(
                Math.pow(data2.getNeck_x() - data1.getNeck_x(), 2) +
                        Math.pow(data2.getNeck_y() - data1.getNeck_y(), 2) +
                        Math.pow(data2.getNeck_z() - data1.getNeck_z(), 2)));
        point.setValueFor(HEAD, Math.sqrt(
                Math.pow(data2.getHead_x() - data1.getHead_x(), 2) +
                        Math.pow(data2.getHead_y() - data1.getHead_y(), 2) +
                        Math.pow(data2.getHead_z() - data1.getHead_z(), 2)));
        point.setValueFor(SHOULDER_LEFT, Math.sqrt(
                Math.pow(data2.getShoulderLeft_x() - data1.getShoulderLeft_x(), 2) +
                        Math.pow(data2.getShoulderLeft_y() - data1.getShoulderLeft_y(), 2) +
                        Math.pow(data2.getShoulderLeft_z() - data1.getShoulderLeft_z(), 2)));
        point.setValueFor(SHOULDER_RIGHT, Math.sqrt(
                Math.pow(data2.getShoulderRight_x() - data1.getShoulderRight_x(), 2) +
                        Math.pow(data2.getShoulderRight_y() - data1.getShoulderRight_y(), 2) +
                        Math.pow(data2.getShoulderRight_z() - data1.getShoulderRight_z(), 2)));
        point.setValueFor(ELBOW_LEFT, Math.sqrt(
                Math.pow(data2.getElbowLeft_x() - data1.getElbowLeft_x(), 2) +
                        Math.pow(data2.getElbowLeft_y() - data1.getElbowLeft_y(), 2) +
                        Math.pow(data2.getElbowLeft_z() - data1.getElbowLeft_z(), 2)));
        point.setValueFor(ELBOW_RIGHT, Math.sqrt(
                Math.pow(data2.getElbowRight_x() - data1.getElbowRight_x(), 2) +
                        Math.pow(data2.getElbowRight_y() - data1.getElbowRight_y(), 2) +
                        Math.pow(data2.getElbowRight_z() - data1.getElbowRight_z(), 2)));
        point.setValueFor(WRIST_LEFT, Math.sqrt(
                Math.pow(data2.getWristLeft_x() - data1.getWristLeft_x(), 2) +
                        Math.pow(data2.getWristLeft_y() - data1.getWristLeft_y(), 2) +
                        Math.pow(data2.getWristLeft_z() - data1.getWristLeft_z(), 2)));
        point.setValueFor(WRIST_RIGHT, Math.sqrt(
                Math.pow(data2.getWristRight_x() - data1.getWristRight_x(), 2) +
                        Math.pow(data2.getWristRight_y() - data1.getWristRight_y(), 2) +
                        Math.pow(data2.getWristRight_z() - data1.getWristRight_z(), 2)));
        point.setValueFor(HAND_LEFT, Math.sqrt(
                Math.pow(data2.getHandLeft_x() - data1.getHandLeft_x(), 2) +
                        Math.pow(data2.getHandLeft_y() - data1.getHandLeft_y(), 2) +
                        Math.pow(data2.getHandLeft_z() - data1.getHandLeft_z(), 2)));
        point.setValueFor(HAND_RIGHT, Math.sqrt(
                Math.pow(data2.getHandRight_x() - data1.getHandRight_x(), 2) +
                        Math.pow(data2.getHandRight_y() - data1.getHandRight_y(), 2) +
                        Math.pow(data2.getHandRight_z() - data1.getHandRight_z(), 2)));
        point.setValueFor(HIP_LEFT, Math.sqrt(
                Math.pow(data2.getHipLeft_x() - data1.getHipLeft_x(), 2) +
                        Math.pow(data2.getHipLeft_y() - data1.getHandLeft_y(), 2) +
                        Math.pow(data2.getHipLeft_z() - data1.getHipLeft_z(), 2)));
        point.setValueFor(HIP_RIGHT, Math.sqrt(
                Math.pow(data2.getHipRight_x() - data1.getHipRight_x(), 2) +
                        Math.pow(data2.getHipRight_y() - data1.getHandRight_y(), 2) +
                        Math.pow(data2.getHipRight_z() - data1.getHipRight_z(), 2)));
        point.setValueFor(KNEE_LEFT, Math.sqrt(
                Math.pow(data2.getKneeLeft_x() - data1.getKneeLeft_x(), 2) +
                        Math.pow(data2.getKneeLeft_y() - data1.getKneeLeft_y(), 2) +
                        Math.pow(data2.getKneeLeft_z() - data1.getKneeLeft_z(), 2)));
        point.setValueFor(KNEE_RIGHT, Math.sqrt(
                Math.pow(data2.getKneeRight_x() - data1.getKneeRight_x(), 2) +
                        Math.pow(data2.getKneeRight_y() - data1.getKneeRight_y(), 2) +
                        Math.pow(data2.getKneeRight_z() - data1.getKneeRight_z(), 2)));
        point.setValueFor(ANKLE_LEFT, Math.sqrt(
                Math.pow(data2.getAnkleLeft_x() - data1.getAnkleLeft_x(), 2) +
                        Math.pow(data2.getAnkleLeft_y() - data1.getAnkleLeft_y(), 2) +
                        Math.pow(data2.getAnkleLeft_z() - data1.getAnkleLeft_z(), 2)));
        point.setValueFor(ANKLE_RIGHT, Math.sqrt(
                Math.pow(data2.getAnkleRight_x() - data1.getAnkleRight_x(), 2) +
                        Math.pow(data2.getAnkleRight_y() - data1.getAnkleRight_y(), 2) +
                        Math.pow(data2.getAnkleRight_z() - data1.getAnkleRight_z(), 2)));
        point.setValueFor(FOOT_LEFT, Math.sqrt(
                Math.pow(data2.getFootLeft_x() - data1.getFootLeft_x(), 2) +
                        Math.pow(data2.getFootLeft_y() - data1.getFootLeft_y(), 2) +
                        Math.pow(data2.getFootLeft_z() - data1.getFootLeft_z(), 2)));
        point.setValueFor(FOOT_RIGHT, Math.sqrt(
                Math.pow(data2.getFootRight_x() - data1.getFootRight_x(), 2) +
                        Math.pow(data2.getFootRight_y() - data1.getFootRight_y(), 2) +
                        Math.pow(data2.getFootRight_z() - data1.getFootRight_z(), 2)));
        point.setValueFor(SPINE_SHOULDER, Math.sqrt(
                Math.pow(data2.getSpineShoulder_x() - data1.getSpineShoulder_x(), 2) +
                        Math.pow(data2.getSpineShoulder_y() - data1.getSpineShoulder_y(), 2) +
                        Math.pow(data2.getSpineShoulder_z() - data1.getSpineShoulder_z(), 2)));
        point.setValueFor(HAND_TIP_LEFT, Math.sqrt(
                Math.pow(data2.getHandTipLeft_x() - data1.getHandTipLeft_x(), 2) +
                        Math.pow(data2.getHandTipLeft_y() - data1.getHandTipLeft_y(), 2) +
                        Math.pow(data2.getHandTipLeft_z() - data1.getHandTipLeft_z(), 2)));
        point.setValueFor(HAND_TIP_RIGHT, Math.sqrt(
                Math.pow(data2.getHandTipRight_x() - data1.getHandTipRight_x(), 2) +
                        Math.pow(data2.getHandTipRight_y() - data1.getHandTipRight_y(), 2) +
                        Math.pow(data2.getHandTipRight_z() - data1.getHandTipRight_z(), 2)));
        point.setValueFor(THUMB_LEFT, Math.sqrt(
                Math.pow(data2.getThumbLeft_x() - data1.getThumbLeft_x(), 2) +
                        Math.pow(data2.getThumbLeft_y() - data1.getThumbLeft_y(), 2) +
                        Math.pow(data2.getThumbLeft_z() - data1.getThumbLeft_z(), 2)));
        point.setValueFor(THUMB_RIGHT, Math.sqrt(
                Math.pow(data2.getThumbRight_x() - data1.getThumbRight_x(), 2) +
                        Math.pow(data2.getThumbRight_y() - data1.getThumbRight_y(), 2) +
                        Math.pow(data2.getThumbRight_z() - data1.getThumbRight_z(), 2)));
    }
}
