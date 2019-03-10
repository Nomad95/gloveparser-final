package org.politechnika.model;

import com.google.common.collect.Lists;

import java.util.List;

import static java.util.stream.Collectors.joining;
import static org.politechnika.commons.Separators.TAB;
import static org.politechnika.model.Finger.*;

public class TimeIntervalHandStatistics {

    private List<HandStatistics> statistics;

    public TimeIntervalHandStatistics() {
        this.statistics = Lists.newArrayList();
    }

    public void addStatstics(HandStatistics handStatistics) {
        statistics.add(handStatistics);
    }

    public void printToConsole() {
        System.out.println("------------- dla okresu ----------");
        System.out.println("Srednia");

        System.out.println("Kciuk: " + statistics.stream().map(stats -> stats.getAverageForFingerView(THUMB)).collect(joining(TAB)));
        System.out.println("Wskazujacy: " + statistics.stream().map(stats -> stats.getAverageForFingerView(INDEX)).collect(joining(TAB)));
        System.out.println("Srodkowy: " + statistics.stream().map(stats -> stats.getAverageForFingerView(MIDDLE)).collect(joining(TAB)));
        System.out.println("Serdeczny: " + statistics.stream().map(stats -> stats.getAverageForFingerView(RING)).collect(joining(TAB)));
        System.out.println("Mały: " + statistics.stream().map(stats -> stats.getAverageForFingerView(LITTLE)).collect(joining(TAB)));

        System.out.println("-----------------------");
        System.out.println("Wariancja");

        System.out.println("Kciuk: " + statistics.stream().map(stats -> stats.getVarianceForFingerView(THUMB)).collect(joining(TAB)));
        System.out.println("Wskazujacy: " + statistics.stream().map(stats -> stats.getVarianceForFingerView(INDEX)).collect(joining(TAB)));
        System.out.println("Srodkowy: " + statistics.stream().map(stats -> stats.getVarianceForFingerView(MIDDLE)).collect(joining(TAB)));
        System.out.println("Serdeczny: " + statistics.stream().map(stats -> stats.getVarianceForFingerView(RING)).collect(joining(TAB)));
        System.out.println("Mały: " + statistics.stream().map(stats -> stats.getVarianceForFingerView(LITTLE)).collect(joining(TAB)));

        System.out.println("-----------------------");
        System.out.println("Odchylenie standardowe");

        System.out.println("Kciuk: " + statistics.stream().map(stats -> stats.getStandardDeviationForFingerView(THUMB)).collect(joining(TAB)));
        System.out.println("Wskazujacy: " + statistics.stream().map(stats -> stats.getStandardDeviationForFingerView(INDEX)).collect(joining(TAB)));
        System.out.println("Srodkowy: " + statistics.stream().map(stats -> stats.getStandardDeviationForFingerView(MIDDLE)).collect(joining(TAB)));
        System.out.println("Serdeczny: " + statistics.stream().map(stats -> stats.getStandardDeviationForFingerView(RING)).collect(joining(TAB)));
        System.out.println("Mały: " + statistics.stream().map(stats -> stats.getStandardDeviationForFingerView(LITTLE)).collect(joining(TAB)));

        System.out.println("-----------------------");
        System.out.println("Wsp Skośnosci");

        System.out.println("Kciuk: " + statistics.stream().map(stats -> stats.getSkewnessForFingerView(THUMB)).collect(joining(TAB)));
        System.out.println("Wskazujacy: " + statistics.stream().map(stats -> stats.getSkewnessForFingerView(INDEX)).collect(joining(TAB)));
        System.out.println("Srodkowy: " + statistics.stream().map(stats -> stats.getSkewnessForFingerView(MIDDLE)).collect(joining(TAB)));
        System.out.println("Serdeczny: " + statistics.stream().map(stats -> stats.getSkewnessForFingerView(RING)).collect(joining(TAB)));
        System.out.println("Mały: " + statistics.stream().map(stats -> stats.getSkewnessForFingerView(LITTLE)).collect(joining(TAB)));

        System.out.println("-----------------------");
        System.out.println("Kurtoza");

        System.out.println("Kciuk: " + statistics.stream().map(stats -> stats.getKurtosisForFingerView(THUMB)).collect(joining(TAB)));
        System.out.println("Wskazujacy: " + statistics.stream().map(stats -> stats.getKurtosisForFingerView(INDEX)).collect(joining(TAB)));
        System.out.println("Srodkowy: " + statistics.stream().map(stats -> stats.getKurtosisForFingerView(MIDDLE)).collect(joining(TAB)));
        System.out.println("Serdeczny: " + statistics.stream().map(stats -> stats.getKurtosisForFingerView(RING)).collect(joining(TAB)));
        System.out.println("Mały: " + statistics.stream().map(stats -> stats.getKurtosisForFingerView(LITTLE)).collect(joining(TAB)));
    }
}
