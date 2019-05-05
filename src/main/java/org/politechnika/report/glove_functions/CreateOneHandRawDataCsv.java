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

@Slf4j
@AllArgsConstructor
public class CreateOneHandRawDataCsv implements UnaryOperator<Map<Finger, List<GloveDataDto>>> {

    private String handName;

    @Override
    public Map<Finger, List<GloveDataDto>> apply(Map<Finger, List<GloveDataDto>> rawLeftHandDataByFinger) {
        log.debug(String.format("Creating %s hand raw data csv", handName));
        DoubleArrayTimeSeries arrayTimeSeries = new AlignGloveSeries().apply(rawLeftHandDataByFinger);

        double[] thumbFingerRawData = arrayTimeSeries.getSeries(Constants.THUMB);
        double[] indexFingerRawData = arrayTimeSeries.getSeries(Constants.INDEX);
        double[] middleFingerRawData = arrayTimeSeries.getSeries(Constants.MIDDLE);
        double[] ringFingerRawData = arrayTimeSeries.getSeries(Constants.RING);
        double[] littleFingerRawData = arrayTimeSeries.getSeries(Constants.LITTLE);

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
