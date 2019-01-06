package org.politechnika.analysis.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GloveGrouper {

    String hand;
    int sensorNumber;
    long epochSeconds;

    public GloveGrouper(String hand, long epochSeconds) {
        this.hand = hand;
        this.epochSeconds = epochSeconds;
    }
}
