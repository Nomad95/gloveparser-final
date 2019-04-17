package org.politechnika.report.impl.glove_functions;

import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.glove.Finger;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.DoubleStream;

import static org.apache.commons.lang3.ArrayUtils.add;
import static org.politechnika.model.glove.Finger.*;

public class CreateTimeSegmentedRightHandRawDataChart implements Function<Map<Finger, List<GloveDataDto>>, Map<Finger, List<GloveDataDto>>> {

    @Override
    public Map<Finger, List<GloveDataDto>> apply(Map<Finger, List<GloveDataDto>> rawRightHandDataByFinger) {
        double[] thumbFingerRawData = rawRightHandDataByFinger.get(THUMB).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] indexFingerRawData = rawRightHandDataByFinger.get(INDEX).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] middleFingerRawData = rawRightHandDataByFinger.get(MIDDLE).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] ringFingerRawData = rawRightHandDataByFinger.get(RING).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] littleFingerRawData = rawRightHandDataByFinger.get(LITTLE).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] timeSeries = DoubleStream.iterate(0, n -> n + 1).limit(thumbFingerRawData.length).toArray();

        int maxLength = rawRightHandDataByFinger.values().stream().mapToInt(List::size).max().orElseThrow(() -> new IllegalStateException("No max value found (?)"));
        if (thumbFingerRawData.length < maxLength) {
            for (int i = 0; i < maxLength - thumbFingerRawData.length; i++) {
                thumbFingerRawData = add(thumbFingerRawData, thumbFingerRawData[thumbFingerRawData.length - 1]);
            }
        }
        if (indexFingerRawData.length < maxLength) {
            for (int i = 0; i < maxLength - indexFingerRawData.length; i++) {
                indexFingerRawData = add(indexFingerRawData, indexFingerRawData[indexFingerRawData.length - 1]);
            }
        }
        if (middleFingerRawData.length < maxLength) {
            for (int i = 0; i < maxLength - middleFingerRawData.length; i++) {
                middleFingerRawData = add(middleFingerRawData, middleFingerRawData[middleFingerRawData.length - 1]);
            }
        }
        if (ringFingerRawData.length < maxLength) {
            for (int i = 0; i < maxLength - ringFingerRawData.length; i++) {
                ringFingerRawData = add(ringFingerRawData, ringFingerRawData[ringFingerRawData.length - 1]);
            }
        }
        if (littleFingerRawData.length < maxLength) {
            for (int i = 0; i < maxLength - littleFingerRawData.length; i++) {
                littleFingerRawData = add(littleFingerRawData, littleFingerRawData[littleFingerRawData.length - 1]);
            }
        }


        drawChart(new Object[]{
                thumbFingerRawData,
                indexFingerRawData,
                middleFingerRawData,
                ringFingerRawData,
                littleFingerRawData
        }, timeSeries);

        return rawRightHandDataByFinger;
    }

    private void drawChart(Object[] dataSeries, double[] timeSeries) {
        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(dataSeries,
                        timeSeries)
                        .withFileName("right_hand_raw_data")
                        .withGrid()
                        .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                        .withTitle("Wartości pobrane z poszczególnych palców prawej ręki")
                        .withXAxisName("Numer wartości")
                        .withYAxisName("Wartości pobrane z rękawicy")
                        .build(MainController.getDestinationSubFolder()));
    }
}
