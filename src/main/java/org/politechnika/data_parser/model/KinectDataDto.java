package org.politechnika.data_parser.model;

import com.opencsv.bean.CsvCustomBindByName;
import lombok.*;
import org.politechnika.data_parser.converter.DoubleWithCommaConverter;
import org.politechnika.data_parser.converter.GloveTimestampToInstantConverter;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class KinectDataDto implements DataDto {

    // SpineBase_x|SpineBase_y|SpineBase_z|
    // SpineMid_x|SpineMid_y|SpineMid_z|
    // Neck_x|Neck_y|Neck_z|
    // Head_x|Head_y|Head_z| //
    // ShoulderLeft_x|ShoulderLeft_y|ShoulderLeft_z|
    // ElbowLeft_x|ElbowLeft_y|ElbowLeft_z|
    // WristLeft_x|WristLeft_y|WristLeft_z|
    // HandLeft_x|HandLeft_y|HandLeft_z|
    // ShoulderRight_x|ShoulderRight_y|ShoulderRight_z|
    // ElbowRight_x|ElbowRight_y|ElbowRight_z|
    // WristRight_x|WristRight_y|WristRight_z|
    // HandRight_x|HandRight_y|HandRight_z| //
    // HipLeft_x|HipLeft_y|HipLeft_z|
    // KneeLeft_x|KneeLeft_y|KneeLeft_z|
    // AnkleLeft_x|AnkleLeft_y|AnkleLeft_z|
    // FootLeft_x|FootLeft_y|FootLeft_z|
    // HipRight_x|HipRight_y|HipRight_z|
    // KneeRight_x|KneeRight_y|KneeRight_z|
    // AnkleRight_x|AnkleRight_y|AnkleRight_z|
    // FootRight_x|FootRight_y|FootRight_z| //
    // SpineShoulder_x|SpineShoulder_y|SpineShoulder_z|
    // HandTipLeft_x|HandTipLeft_y|HandTipLeft_z|
    // ThumbLeft_x|ThumbLeft_y|ThumbLeft_z|
    // HandTipRight_x|HandTipRight_y|HandTipRight_z|
    // ThumbRight_x|ThumbRight_y|ThumbRight_z|
    // TimeStamp

    @CsvCustomBindByName(column = "SpineBase_x", converter = DoubleWithCommaConverter.class)
    private double spineBase_x;

    @CsvCustomBindByName(column = "SpineBase_y", converter = DoubleWithCommaConverter.class)
    private double spineBase_y;

    @CsvCustomBindByName(column = "SpineBase_z", converter = DoubleWithCommaConverter.class)
    private double spineBase_z;

    @CsvCustomBindByName(column = "SpineMid_x", converter = DoubleWithCommaConverter.class)
    private double spineMid_x;

    @CsvCustomBindByName(column = "SpineMid_y", converter = DoubleWithCommaConverter.class)
    private double spineMid_y;

    @CsvCustomBindByName(column = "SpineMid_z", converter = DoubleWithCommaConverter.class)
    private double spineMid_z;

    @CsvCustomBindByName(column = "Neck_x", converter = DoubleWithCommaConverter.class)
    private double neck_x;

    @CsvCustomBindByName(column = "Neck_y", converter = DoubleWithCommaConverter.class)
    private double neck_y;

    @CsvCustomBindByName(column = "Neck_z", converter = DoubleWithCommaConverter.class)
    private double neck_z;

    @CsvCustomBindByName(column = "Head_x", converter = DoubleWithCommaConverter.class)
    private double head_x;

    @CsvCustomBindByName(column = "Head_y", converter = DoubleWithCommaConverter.class)
    private double head_y;

    @CsvCustomBindByName(column = "Head_z", converter = DoubleWithCommaConverter.class)
    private double head_z;

    @CsvCustomBindByName(column = "ShoulderLeft_x", converter = DoubleWithCommaConverter.class)
    private double shoulderLeft_x;

    @CsvCustomBindByName(column = "ShoulderLeft_y", converter = DoubleWithCommaConverter.class)
    private double shoulderLeft_y;

    @CsvCustomBindByName(column = "ShoulderLeft_z", converter = DoubleWithCommaConverter.class)
    private double shoulderLeft_z;

    @CsvCustomBindByName(column = "ElbowLeft_x", converter = DoubleWithCommaConverter.class)
    private double elbowLeft_x;

    @CsvCustomBindByName(column = "ElbowLeft_y", converter = DoubleWithCommaConverter.class)
    private double elbowLeft_y;

    @CsvCustomBindByName(column = "ElbowLeft_z", converter = DoubleWithCommaConverter.class)
    private double elbowLeft_z;

    @CsvCustomBindByName(column = "WristLeft_x", converter = DoubleWithCommaConverter.class)
    private double wristLeft_x;

    @CsvCustomBindByName(column = "WristLeft_y", converter = DoubleWithCommaConverter.class)
    private double wristLeft_y;

    @CsvCustomBindByName(column = "WristLeft_z", converter = DoubleWithCommaConverter.class)
    private double wristLeft_z;

    @CsvCustomBindByName(column = "HandLeft_x", converter = DoubleWithCommaConverter.class)
    private double handLeft_x;

    @CsvCustomBindByName(column = "HandLeft_y", converter = DoubleWithCommaConverter.class)
    private double handLeft_y;

    @CsvCustomBindByName(column = "HandLeft_z", converter = DoubleWithCommaConverter.class)
    private double handLeft_z;

    @CsvCustomBindByName(column = "ShoulderRight_x", converter = DoubleWithCommaConverter.class)
    private double shoulderRight_x;

    @CsvCustomBindByName(column = "ShoulderRight_y", converter = DoubleWithCommaConverter.class)
    private double shoulderRight_y;

    @CsvCustomBindByName(column = "ShoulderRight_z", converter = DoubleWithCommaConverter.class)
    private double shoulderRight_z;

    @CsvCustomBindByName(column = "ElbowRight_x", converter = DoubleWithCommaConverter.class)
    private double elbowRight_x;

    @CsvCustomBindByName(column = "ElbowRight_y", converter = DoubleWithCommaConverter.class)
    private double elbowRight_y;

    @CsvCustomBindByName(column = "ElbowRight_z", converter = DoubleWithCommaConverter.class)
    private double elbowRight_z;

    @CsvCustomBindByName(column = "WristRight_x", converter = DoubleWithCommaConverter.class)
    private double wristRight_x;

    @CsvCustomBindByName(column = "WristRight_y", converter = DoubleWithCommaConverter.class)
    private double wristRight_y;

    @CsvCustomBindByName(column = "WristRight_z", converter = DoubleWithCommaConverter.class)
    private double wristRight_z;

    @CsvCustomBindByName(column = "HandRight_x", converter = DoubleWithCommaConverter.class)
    private double handRight_x;

    @CsvCustomBindByName(column = "HandRight_y", converter = DoubleWithCommaConverter.class)
    private double handRight_y;

    @CsvCustomBindByName(column = "HandRight_z", converter = DoubleWithCommaConverter.class)
    private double handRight_z;

    @CsvCustomBindByName(column = "HipLeft_x", converter = DoubleWithCommaConverter.class)
    private double hipLeft_x;

    @CsvCustomBindByName(column = "HipLeft_y", converter = DoubleWithCommaConverter.class)
    private double hipLeft_y;

    @CsvCustomBindByName(column = "HipLeft_z", converter = DoubleWithCommaConverter.class)
    private double hipLeft_z;

    @CsvCustomBindByName(column = "KneeLeft_x", converter = DoubleWithCommaConverter.class)
    private double kneeLeft_x;

    @CsvCustomBindByName(column = "KneeLeft_y", converter = DoubleWithCommaConverter.class)
    private double kneeLeft_y;

    @CsvCustomBindByName(column = "KneeLeft_z", converter = DoubleWithCommaConverter.class)
    private double kneeLeft_z;

    @CsvCustomBindByName(column = "AnkleLeft_x", converter = DoubleWithCommaConverter.class)
    private double ankleLeft_x;

    @CsvCustomBindByName(column = "AnkleLeft_y", converter = DoubleWithCommaConverter.class)
    private double ankleLeft_y;

    @CsvCustomBindByName(column = "AnkleLeft_z", converter = DoubleWithCommaConverter.class)
    private double ankleLeft_z;

    @CsvCustomBindByName(column = "FootLeft_x", converter = DoubleWithCommaConverter.class)
    private double footLeft_x;

    @CsvCustomBindByName(column = "FootLeft_y", converter = DoubleWithCommaConverter.class)
    private double footLeft_y;

    @CsvCustomBindByName(column = "FootLeft_z", converter = DoubleWithCommaConverter.class)
    private double footLeft_z;

    @CsvCustomBindByName(column = "HipRight_x", converter = DoubleWithCommaConverter.class)
    private double hipRight_x;

    @CsvCustomBindByName(column = "HipRight_y", converter = DoubleWithCommaConverter.class)
    private double hipRight_y;

    @CsvCustomBindByName(column = "HipRight_z", converter = DoubleWithCommaConverter.class)
    private double hipRight_z;

    @CsvCustomBindByName(column = "KneeRight_x", converter = DoubleWithCommaConverter.class)
    private double kneeRight_x;

    @CsvCustomBindByName(column = "KneeRight_y", converter = DoubleWithCommaConverter.class)
    private double kneeRight_y;

    @CsvCustomBindByName(column = "KneeRight_z", converter = DoubleWithCommaConverter.class)
    private double kneeRight_z;

    @CsvCustomBindByName(column = "AnkleRight_x", converter = DoubleWithCommaConverter.class)
    private double ankleRight_x;

    @CsvCustomBindByName(column = "AnkleRight_y", converter = DoubleWithCommaConverter.class)
    private double ankleRight_y;

    @CsvCustomBindByName(column = "AnkleRight_z", converter = DoubleWithCommaConverter.class)
    private double ankleRight_z;

    @CsvCustomBindByName(column = "FootRight_x", converter = DoubleWithCommaConverter.class)
    private double footRight_x;

    @CsvCustomBindByName(column = "FootRight_y", converter = DoubleWithCommaConverter.class)
    private double footRight_y;

    @CsvCustomBindByName(column = "FootRight_z", converter = DoubleWithCommaConverter.class)
    private double footRight_z;

    @CsvCustomBindByName(column = "SpineShoulder_x", converter = DoubleWithCommaConverter.class)
    private double spineShoulder_x;

    @CsvCustomBindByName(column = "SpineShoulder_y", converter = DoubleWithCommaConverter.class)
    private double spineShoulder_y;

    @CsvCustomBindByName(column = "SpineShoulder_z", converter = DoubleWithCommaConverter.class)
    private double spineShoulder_z;

    @CsvCustomBindByName(column = "HandTipLeft_x", converter = DoubleWithCommaConverter.class)
    private double handTipLeft_x;

    @CsvCustomBindByName(column = "HandTipLeft_y", converter = DoubleWithCommaConverter.class)
    private double handTipLeft_y;

    @CsvCustomBindByName(column = "HandTipLeft_z", converter = DoubleWithCommaConverter.class)
    private double handTipLeft_z;

    @CsvCustomBindByName(column = "ThumbLeft_x", converter = DoubleWithCommaConverter.class)
    private double thumbLeft_x;

    @CsvCustomBindByName(column = "ThumbLeft_y", converter = DoubleWithCommaConverter.class)
    private double thumbLeft_y;

    @CsvCustomBindByName(column = "ThumbLeft_z", converter = DoubleWithCommaConverter.class)
    private double thumbLeft_z;

    @CsvCustomBindByName(column = "HandTipRight_x", converter = DoubleWithCommaConverter.class)
    private double handTipRight_x;

    @CsvCustomBindByName(column = "HandTipRight_y", converter = DoubleWithCommaConverter.class)
    private double handTipRight_y;

    @CsvCustomBindByName(column = "HandTipRight_z", converter = DoubleWithCommaConverter.class)
    private double handTipRight_z;

    @CsvCustomBindByName(column = "ThumbRight_x", converter = DoubleWithCommaConverter.class)
    private double thumbRight_x;

    @CsvCustomBindByName(column = "ThumbRight_y", converter = DoubleWithCommaConverter.class)
    private double thumbRight_y;

    @CsvCustomBindByName(column = "ThumbRight_z", converter = DoubleWithCommaConverter.class)
    private double thumbRight_z;

    @CsvCustomBindByName(column = "TimeStamp", converter = GloveTimestampToInstantConverter.class)
    private Instant timestamp;
}
