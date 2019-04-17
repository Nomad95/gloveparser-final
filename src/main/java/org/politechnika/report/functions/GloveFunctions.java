package org.politechnika.report.functions;

import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.model.glove.Finger;

import java.util.function.Function;

public final class GloveFunctions  {

    public static Function<GloveDataDto, Finger> sensorToFinger() {
        return dto -> Finger.getFingerBySensorNumber(dto.getSensorNumber());
    }
}
