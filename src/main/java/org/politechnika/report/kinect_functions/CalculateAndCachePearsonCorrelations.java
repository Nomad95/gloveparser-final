package org.politechnika.report.kinect_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.analysis.CorrelationAnalyzer;
import org.politechnika.analysis.StandardCorrelationAnalyzer;
import org.politechnika.cache.LoadingStringCache;
import org.politechnika.commons.Separators;
import org.politechnika.model.kinect.PointDistance;
import org.politechnika.processing.DoubleArrayTimeSeries;

import java.util.List;
import java.util.function.UnaryOperator;

import static org.politechnika.cache.EntryType.KINECT_CORRELATIONS;
import static org.politechnika.model.kinect.Sensor.*;

@Slf4j
public class CalculateAndCachePearsonCorrelations implements UnaryOperator<List<PointDistance>> {

    @Override
    public List<PointDistance> apply(List<PointDistance> kinectDataDtos) {
        log.debug("Calculating point distance Pearson's correlation matrix");
        DoubleArrayTimeSeries arrayTimeSeries = new DoubleArrayTimeSeries();
        arrayTimeSeries.addSeries(SPINE_BASE.name(), kinectDataDtos.stream().mapToDouble(v -> v.getSpineBase()).toArray());
        arrayTimeSeries.addSeries(SPINE_MID.name(), kinectDataDtos.stream().mapToDouble(v -> v.getSpineMid()).toArray());
        arrayTimeSeries.addSeries(NECK.name(), kinectDataDtos.stream().mapToDouble(v -> v.getNeck()).toArray());
        arrayTimeSeries.addSeries(HEAD.name(), kinectDataDtos.stream().mapToDouble(v -> v.getHead()).toArray());
        arrayTimeSeries.addSeries(SHOULDER_LEFT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getShoulderLeft()).toArray());
        arrayTimeSeries.addSeries(SHOULDER_RIGHT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getShoulderRight()).toArray());
        arrayTimeSeries.addSeries(ELBOW_LEFT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getElbowLeft()).toArray());
        arrayTimeSeries.addSeries(ELBOW_RIGHT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getElbowRight()).toArray());
        arrayTimeSeries.addSeries(WRIST_LEFT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getWristLeft()).toArray());
        arrayTimeSeries.addSeries(WRIST_RIGHT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getWristRight()).toArray());
        arrayTimeSeries.addSeries(HAND_LEFT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getHandLeft()).toArray());
        arrayTimeSeries.addSeries(HAND_RIGHT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getHandRight()).toArray());
        arrayTimeSeries.addSeries(HIP_LEFT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getHipLeft()).toArray());
        arrayTimeSeries.addSeries(HIP_RIGHT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getHipRight()).toArray());
        arrayTimeSeries.addSeries(KNEE_LEFT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getKneeLeft()).toArray());
        arrayTimeSeries.addSeries(KNEE_RIGHT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getKneeRight()).toArray());
        arrayTimeSeries.addSeries(ANKLE_LEFT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getAnkleLeft()).toArray());
        arrayTimeSeries.addSeries(ANKLE_RIGHT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getAnkleRight()).toArray());
        arrayTimeSeries.addSeries(FOOT_LEFT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getFootLeft()).toArray());
        arrayTimeSeries.addSeries(FOOT_RIGHT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getFootRight()).toArray());
        arrayTimeSeries.addSeries(SPINE_SHOULDER.name(), kinectDataDtos.stream().mapToDouble(v -> v.getSpineShoulder()).toArray());
        arrayTimeSeries.addSeries(HAND_TIP_LEFT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getHandTipLeft()).toArray());
        arrayTimeSeries.addSeries(HAND_TIP_RIGHT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getHandTipRight()).toArray());
        arrayTimeSeries.addSeries(THUMB_LEFT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getThumbLeft()).toArray());
        arrayTimeSeries.addSeries(THUMB_RIGHT.name(), kinectDataDtos.stream().mapToDouble(v -> v.getThumbRight()).toArray());

        double[][] corrTable = calculateCorrTable(arrayTimeSeries);
        StringBuilder sb = createStringRepresentation(corrTable);

        LoadingStringCache.put(KINECT_CORRELATIONS, sb.toString());

        return kinectDataDtos;
    }

    private double[][] calculateCorrTable(DoubleArrayTimeSeries arrayTimeSeries) {
        String[] kinect = arrayTimeSeries.getInsertOrderKeys();
        CorrelationAnalyzer corrAnalyzer = new StandardCorrelationAnalyzer();
        double[][] pearsonTable = new double[25][25];
        for (int i = 0; i < pearsonTable.length; i++) {
            for (int j = 0; j < pearsonTable[i].length; j++) {
                pearsonTable[i][j] = corrAnalyzer.getPearsonCorrelation(
                        arrayTimeSeries.getSeries(kinect[i]),
                        arrayTimeSeries.getSeries(kinect[j])
                );
            }
        }
        return pearsonTable;
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
}
