package org.politechnika.report.glove_functions;

import org.politechnika.commons.Constants;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.model.glove.Finger;
import org.politechnika.processing.DoubleArrayTimeSeries;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.Collections.emptyList;
import static org.politechnika.model.glove.Finger.*;
import static org.politechnika.processing.DoubleArrayTimeSeries.AligningMode.LAST_VALUE;

public class AlignGloveSeries implements Function<Map<Finger, List<GloveDataDto>>, DoubleArrayTimeSeries> {

    @Override
    public DoubleArrayTimeSeries apply(Map<Finger, List<GloveDataDto>> fingerListMap) {
        double[] thumbFingerRawData = fingerListMap.getOrDefault(THUMB, emptyList()).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] indexFingerRawData = fingerListMap.getOrDefault(INDEX, emptyList()).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] middleFingerRawData = fingerListMap.getOrDefault(MIDDLE, emptyList()).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] ringFingerRawData = fingerListMap.getOrDefault(RING, emptyList()).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] littleFingerRawData = fingerListMap.getOrDefault(LITTLE, emptyList()).stream().mapToDouble(GloveDataDto::getRaw).toArray();

        DoubleArrayTimeSeries arrayTimeSeries = new DoubleArrayTimeSeries();
        arrayTimeSeries.addSeries(Constants.THUMB, thumbFingerRawData);
        arrayTimeSeries.addSeries(Constants.INDEX, indexFingerRawData);
        arrayTimeSeries.addSeries(Constants.MIDDLE, middleFingerRawData);
        arrayTimeSeries.addSeries(Constants.RING, ringFingerRawData);
        arrayTimeSeries.addSeries(Constants.LITTLE, littleFingerRawData);
        arrayTimeSeries.alignArrays(LAST_VALUE);

        return arrayTimeSeries;
    }
}
