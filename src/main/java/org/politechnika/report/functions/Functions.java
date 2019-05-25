package org.politechnika.report.functions;

import lombok.NoArgsConstructor;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.model.glove.Finger;
import org.politechnika.model.glove.GloveValueDto;
import org.politechnika.model.kinect.PointDistanceValueDto;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class Functions {

    public static Function<GloveDataDto, Finger> sensorToFinger() {
        return dto -> Finger.getFingerBySensorNumber(dto.getSensorNumber());
    }

    public static ToDoubleFunction<PointDistanceValueDto> kinectToAllAvg() {
        return p -> (p.getHead() + p.getFootLeft() + p.getFootRight() + p.getHandLeft() + p.getHandRight()) / 5;
    }

    public static ToDoubleFunction<GloveValueDto> gloveToAllAvg() {
        return p -> (p.getThumb() + p.getIndex() + p.getMiddle() + p.getRing() + p.getLittle()) / 5;
    }
}
