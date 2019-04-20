package org.politechnika.report.glove_functions;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.Constants;
import org.politechnika.data_parser.CsvParsingException;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.data_parser.model.OneHandGloveRawData;
import org.politechnika.file.FileWriter;
import org.politechnika.model.glove.Finger;
import org.politechnika.processing.DoubleArrayTimeSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import static java.lang.String.format;
import static org.politechnika.model.glove.Finger.*;
import static org.politechnika.processing.DoubleArrayTimeSeries.AligningMode.LAST_VALUE;

@Slf4j
@AllArgsConstructor
public class CreateOneHandRawDataCsv implements UnaryOperator<Map<Finger, List<GloveDataDto>>> {

    private String handName;

    @Override
    public Map<Finger, List<GloveDataDto>> apply(Map<Finger, List<GloveDataDto>> rawLeftHandDataByFinger) {
        log.debug(String.format("Creating %s hand raw data csv", handName));
        double[] thumbFingerRawData = rawLeftHandDataByFinger.get(THUMB).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] indexFingerRawData = rawLeftHandDataByFinger.get(INDEX).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] middleFingerRawData = rawLeftHandDataByFinger.get(MIDDLE).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] ringFingerRawData = rawLeftHandDataByFinger.get(RING).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] littleFingerRawData = rawLeftHandDataByFinger.get(LITTLE).stream().mapToDouble(GloveDataDto::getRaw).toArray();

        DoubleArrayTimeSeries arrayTimeSeries = new DoubleArrayTimeSeries();
        arrayTimeSeries.addSeries(Constants.THUMB, thumbFingerRawData);
        arrayTimeSeries.addSeries(Constants.INDEX, indexFingerRawData);
        arrayTimeSeries.addSeries(Constants.MIDDLE, middleFingerRawData);
        arrayTimeSeries.addSeries(Constants.RING, ringFingerRawData);
        arrayTimeSeries.addSeries(Constants.LITTLE, littleFingerRawData);
        arrayTimeSeries.alignArrays(LAST_VALUE);

        thumbFingerRawData = arrayTimeSeries.getSeries(Constants.THUMB);
        indexFingerRawData = arrayTimeSeries.getSeries(Constants.INDEX);
        middleFingerRawData = arrayTimeSeries.getSeries(Constants.MIDDLE);
        ringFingerRawData = arrayTimeSeries.getSeries(Constants.RING);
        littleFingerRawData = arrayTimeSeries.getSeries(Constants.LITTLE);

        ArrayList<OneHandGloveRawData> res = new ArrayList<>();
        for (int i = 0; i < thumbFingerRawData.length; i++) {
            res.add(new OneHandGloveRawData(
                    thumbFingerRawData[i],
                    indexFingerRawData[i],
                    middleFingerRawData[i],
                    ringFingerRawData[i],
                    littleFingerRawData[i]
            ));
        }

        tryWriteDataToCsv(res);

        return rawLeftHandDataByFinger;
    }

    private void tryWriteDataToCsv(ArrayList<OneHandGloveRawData> res) {
        try {
            new FileWriter().writeToCsvFile(res, format("/raw_data_glove_%s_hand.csv", handName));
        } catch (CsvParsingException e) {
            log.error("Could not write glove csv. {}", e.getMessage());
        }
    }
}
