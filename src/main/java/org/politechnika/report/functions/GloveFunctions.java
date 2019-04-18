package org.politechnika.report.functions;

import lombok.NoArgsConstructor;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.model.glove.Finger;

import java.util.function.Function;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class GloveFunctions  {

    public static Function<GloveDataDto, Finger> sensorToFinger() {
        return dto -> Finger.getFingerBySensorNumber(dto.getSensorNumber());
    }
}
