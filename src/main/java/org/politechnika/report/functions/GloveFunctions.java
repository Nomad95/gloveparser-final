package org.politechnika.report.functions;

import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;
import org.politechnika.model.Finger;

import java.util.function.Function;

public final class GloveFunctions  {

    public static Function<GloveDataDto, Finger> sensorToFinger() {
        return dto -> Finger.getFingerBySensorNumber(dto.getSensorNumber());
    }
}
