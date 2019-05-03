package org.politechnika.report.glove_functions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.analysis.CorrelationAnalyzer;
import org.politechnika.analysis.StandardCorrelationAnalyzer;
import org.politechnika.cache.EntryType;
import org.politechnika.cache.LoadingStringCache;
import org.politechnika.commons.Separators;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.model.glove.Finger;
import org.politechnika.processing.DoubleArrayTimeSeries;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

@Slf4j
@AllArgsConstructor
public class CalculateAndCachePearsonCorrelations implements UnaryOperator<Map<Finger, List<GloveDataDto>>> {

    private String handName;

    @Override
    public Map<Finger, List<GloveDataDto>> apply(Map<Finger, List<GloveDataDto>> rawHandDataByFinger) {
        log.debug(String.format("Calculating %s hand Pearson's correlation matrix", handName));
        DoubleArrayTimeSeries arrayTimeSeries = new AlignGloveSeries().apply(rawHandDataByFinger);
        double[][] pearsonTable = calculateCorrTable(arrayTimeSeries);
        StringBuilder sb = createStringRepresentation(pearsonTable);

        if ("left".equals(handName))
            LoadingStringCache.put(EntryType.LEFT_HAND_CORRELATIONS, sb.toString());
        else
            LoadingStringCache.put(EntryType.RIGHT_HAND_CORRELATIONS, sb.toString());

        return rawHandDataByFinger;
    }

    private StringBuilder createStringRepresentation(double[][] pearsonTable) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < pearsonTable.length; i++) {
            for (int j = 0; j < pearsonTable[i].length; j++) {
                sb.append(pearsonTable[i][j]).append(Separators.TAB).append(Separators.TAB);
            }
            sb.append(Separators.NEWLINE);
        }
        return sb;
    }

    private double[][] calculateCorrTable(DoubleArrayTimeSeries arrayTimeSeries) {
        String[] fingers = arrayTimeSeries.getInsertOrderKeys();
        CorrelationAnalyzer corrAnalyzer = new StandardCorrelationAnalyzer();
        double[][] pearsonTable = new double[5][5];
        for (int i = 0; i < pearsonTable.length; i++) {
            for (int j = 0; j < pearsonTable[i].length; j++) {
                pearsonTable[i][j] = corrAnalyzer.getPearsonCorrelation(
                        arrayTimeSeries.getSeries(fingers[i]),
                        arrayTimeSeries.getSeries(fingers[j])
                );
            }
        }
        return pearsonTable;
    }


}
