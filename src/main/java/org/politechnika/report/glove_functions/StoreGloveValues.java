package org.politechnika.report.glove_functions;

import lombok.AllArgsConstructor;
import org.politechnika.cache.EntryType;
import org.politechnika.cache.LoadingDataCache;
import org.politechnika.commons.Constants;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.model.glove.Finger;
import org.politechnika.model.glove.GloveValueDto;
import org.politechnika.processing.DoubleArrayTimeSeries;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static org.politechnika.model.glove.Finger.*;
import static org.politechnika.processing.DoubleArrayTimeSeries.AligningMode.LAST_VALUE;

@AllArgsConstructor
public class StoreGloveValues implements UnaryOperator<Map<Finger, List<GloveDataDto>>> {

    private final EntryType type;

    @Override
    public Map<Finger, List<GloveDataDto>> apply(Map<Finger, List<GloveDataDto>> dataByFinger) {
        double[] thumbFingerRawData = dataByFinger.get(THUMB).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] indexFingerRawData = dataByFinger.get(INDEX).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] middleFingerRawData = dataByFinger.get(MIDDLE).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] ringFingerRawData = dataByFinger.get(RING).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] littleFingerRawData = dataByFinger.get(LITTLE).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        List<Instant> normalizedTime = dataByFinger.get(THUMB).stream().map(GloveDataDto::getTimestamp).collect(Collectors.toList());

        DoubleArrayTimeSeries arrayTimeSeries = new DoubleArrayTimeSeries();
        arrayTimeSeries.addSeries(Constants.THUMB, thumbFingerRawData);
        arrayTimeSeries.addSeries(Constants.INDEX, indexFingerRawData);
        arrayTimeSeries.addSeries(Constants.MIDDLE, middleFingerRawData);
        arrayTimeSeries.addSeries(Constants.RING, ringFingerRawData);
        arrayTimeSeries.addSeries(Constants.LITTLE, littleFingerRawData);
        arrayTimeSeries.alignArrays(LAST_VALUE);
        arrayTimeSeries.adjustListToSeriesLength(normalizedTime);

        thumbFingerRawData = arrayTimeSeries.getSeries(Constants.THUMB);
        indexFingerRawData = arrayTimeSeries.getSeries(Constants.INDEX);
        middleFingerRawData = arrayTimeSeries.getSeries(Constants.MIDDLE);
        ringFingerRawData = arrayTimeSeries.getSeries(Constants.RING);
        littleFingerRawData = arrayTimeSeries.getSeries(Constants.LITTLE);

        LinkedList<GloveValueDto> values = new LinkedList<>();
        for (int i = 0; i < normalizedTime.toArray(new Instant[0]).length; i++) {
            values.add(new GloveValueDto(
                    thumbFingerRawData[i],
                    indexFingerRawData[i],
                    middleFingerRawData[i],
                    ringFingerRawData[i],
                    littleFingerRawData[i],
                    normalizedTime.get(i)
            ));
        }

        LoadingDataCache.put(type, values);
        return dataByFinger;
    }
}
