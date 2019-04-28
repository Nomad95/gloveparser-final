package org.politechnika.model.pulsometer;

import lombok.Value;

import static org.politechnika.commons.Separators.NEWLINE;
import static org.politechnika.commons.Separators.TAB;

@Value
public class PulsometerStatistics {
    private double average;
    private double stdDeviation;
    private double variance;
    private double skewness;
    private double kurtosis;

    public String toReportString() {
        return new StringBuilder("Srednia arytmetyczna: ").append(TAB).append(average).append(NEWLINE)
                .append("Odchylenie standardowe: ").append(TAB).append(stdDeviation).append(NEWLINE)
                .append("Wariancja: ").append(TAB).append(variance).append(NEWLINE)
                .append("Wsp. Skośności: ").append(TAB).append(skewness).append(NEWLINE)
                .append("Kurtoza: ").append(TAB).append(kurtosis).append(NEWLINE)
                .toString();
    }
}
