package org.politechnika.model.glove;

import lombok.Value;
import org.politechnika.data_parser.model.DataDto;
import org.politechnika.data_parser.model.TimeSequential;

import java.time.Instant;

@Value
public class GloveValueDto implements DataDto, TimeSequential {
    private double thumb;
    private double index;
    private double middle;
    private double ring;
    private double little;
    private Instant time;

    public GloveValueDto moveInTime(Instant newTime) {
        return new GloveValueDto(thumb, index, middle, ring, little, newTime);
    }
}
