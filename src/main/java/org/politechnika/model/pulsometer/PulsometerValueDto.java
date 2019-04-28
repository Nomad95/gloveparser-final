package org.politechnika.model.pulsometer;

import lombok.Value;
import org.politechnika.data_parser.model.DataDto;
import org.politechnika.data_parser.model.TimeSequential;

import java.time.Instant;

@Value
public class PulsometerValueDto implements DataDto, TimeSequential {
    private Instant time;
    private double value;

    public PulsometerValueDto moveInTime(Instant newTime) {
        return new PulsometerValueDto(newTime, value);
    }

}
