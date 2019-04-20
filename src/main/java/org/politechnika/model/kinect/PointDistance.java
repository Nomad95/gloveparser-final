package org.politechnika.model.kinect;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.politechnika.data_parser.model.DataDto;

import java.time.Instant;

@Data
@RequiredArgsConstructor
public class PointDistance implements DataDto {

    private final String description;

    @CsvBindByName(column = "odcinek_ledzwiowy_kregoslupa")
    private double spineBase;

    @CsvBindByName(column = "odcinek_srodkowy_kregoslupa")
    private double spineMid;

    @CsvBindByName(column = "szyja")
    private double neck;

    @CsvBindByName(column = "glowa")
    private double head;

    @CsvBindByName(column = "ramie_lewe")
    private double shoulderLeft;

    @CsvBindByName(column = "lewy_lokiec")
    private double elbowLeft;

    @CsvBindByName(column = "lewy_nadgarstek")
    private double wristLeft;

    @CsvBindByName(column = "lewa_reka")
    private double handLeft;

    @CsvBindByName(column = "ramie_prawe")
    private double shoulderRight;

    @CsvBindByName(column = "prawy_lokiec")
    private double elbowRight;

    @CsvBindByName(column = "prawy_nadgarstek")
    private double wristRight;

    @CsvBindByName(column = "prawa_reka")
    private double handRight;

    @CsvBindByName(column = "lewe_biodro")
    private double hipLeft;

    @CsvBindByName(column = "lewe_kolano")
    private double kneeLeft;

    @CsvBindByName(column = "lewa_kostka")
    private double ankleLeft;

    @CsvBindByName(column = "lewa_stopa")
    private double footLeft;

    @CsvBindByName(column = "prawe_biodro")
    private double hipRight;

    @CsvBindByName(column = "prawe_kolano")
    private double kneeRight;

    @CsvBindByName(column = "prawa_kostka")
    private double ankleRight;

    @CsvBindByName(column = "prawa_stopa")
    private double footRight;

    @CsvBindByName(column = "odcinek_szyjny_kregoslupa")
    private double spineShoulder;

    @CsvBindByName(column = "palec_wskazujacy_lewy")
    private double handTipLeft;

    @CsvBindByName(column = "kciuk_lewy")
    private double thumbLeft;

    @CsvBindByName(column = "palec_wskazujacy_prawy")
    private double handTipRight;

    @CsvBindByName(column = "kciuk_prawy")
    private double thumbRight;

    private Instant time;

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

     double getValueFor(Sensor sensor) {
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
