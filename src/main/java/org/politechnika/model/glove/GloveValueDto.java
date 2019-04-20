package org.politechnika.model.glove;

import lombok.Value;
import org.politechnika.data_parser.model.DataDto;

import java.time.Instant;

@Value
public class GloveValueDto implements DataDto {
    private double thumb;
    private double index;
    private double middle;
    private double ring;
    private double little;
    private Instant time;
}
