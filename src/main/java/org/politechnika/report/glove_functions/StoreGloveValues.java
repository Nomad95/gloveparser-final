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

import static org.politechnika.model.glove.Finger.THUMB;

@AllArgsConstructor
public class StoreGloveValues implements UnaryOperator<Map<Finger, List<GloveDataDto>>> {

    private final EntryType type;

    @Override
    public Map<Finger, List<GloveDataDto>> apply(Map<Finger, List<GloveDataDto>> dataByFinger) {
        DoubleArrayTimeSeries arrayTimeSeries = new AlignGloveSeries().apply(dataByFinger);
        List<Instant> normalizedTime = dataByFinger.get(THUMB).stream().map(GloveDataDto::getTimestamp).collect(Collectors.toList());
        arrayTimeSeries.adjustListToSeriesLength(normalizedTime);

        double[] thumbFingerRawData = arrayTimeSeries.getSeries(Constants.THUMB);
        double[] indexFingerRawData = arrayTimeSeries.getSeries(Constants.INDEX);
        double[] middleFingerRawData = arrayTimeSeries.getSeries(Constants.MIDDLE);
        double[] ringFingerRawData = arrayTimeSeries.getSeries(Constants.RING);
        double[] littleFingerRawData = arrayTimeSeries.getSeries(Constants.LITTLE);

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
