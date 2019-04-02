package org.politechnika.report.impl.glove_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;
import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.Finger;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.DoubleStream;

import static org.apache.commons.lang3.ArrayUtils.add;
import static org.politechnika.model.Finger.*;

@Slf4j
public class CreateTimeSegmentedLeftHandRawDataChart implements Function<Map<Finger, List<GloveDataDto>>, Map<Finger, List<GloveDataDto>>> {

    @Override
    public Map<Finger, List<GloveDataDto>> apply(Map<Finger, List<GloveDataDto>> rawLeftHandDataByFinger) {
        log.debug("Creating raw data chart for left hand");
        double[] thumbFingerRawData = rawLeftHandDataByFinger.get(THUMB).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] indexFingerRawData = rawLeftHandDataByFinger.get(INDEX).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] middleFingerRawData = rawLeftHandDataByFinger.get(MIDDLE).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] ringFingerRawData = rawLeftHandDataByFinger.get(RING).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] littleFingerRawData = rawLeftHandDataByFinger.get(LITTLE).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] timeSeries = DoubleStream.iterate(0, n -> n + 1).limit(thumbFingerRawData.length).toArray();

        //taki workaround bo te serie danych mogą miec rozna dlugosc jesli plik sie nie rowno konczy - wypelniamy do konca wartosciami
        int maxLength = rawLeftHandDataByFinger.values().stream().mapToInt(List::size).max().orElseThrow(() -> new IllegalStateException("No max value found (?)"));
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

        new ChartGeneratorImpl().drawChart(
                new Plot.Builder(
                        new Object[]{
                                thumbFingerRawData,
                                indexFingerRawData,
                                middleFingerRawData,
                                ringFingerRawData,
                                littleFingerRawData
                        }, timeSeries)
                        .withFileName("left_hand_raw_data")
                        .withGrid()
                        .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                        .withTitle("Wartości pobrane z poszczególnych palców lewej ręki")
                        .withXAxisName("Czas [s]")
                        .withYAxisName("Wartości pobrane z rękawicy")
                        .build(MainController.getDestinationSubFolder()));

        log.debug("Chart generated");
        return rawLeftHandDataByFinger;
    }
}
