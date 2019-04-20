package org.politechnika.model.pulsometer;

import lombok.Value;
import org.politechnika.data_parser.model.DataDto;

import java.time.Instant;

@Value
public class PulsometerValueDto implements DataDto {
    private Instant time;
    private int value;
}
