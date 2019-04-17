package org.politechnika.report.impl.glove_functions;

import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;
import org.politechnika.data_parser.csv.definitions.beans.OneHandGloveRawData;
import org.politechnika.data_parser.csv.impl.CsvBeanParser;
import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.model.Finger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.apache.commons.lang3.ArrayUtils.add;
import static org.politechnika.model.Finger.*;

public class CreateRightHandRawDataCsv implements Function<Map<Finger, List<GloveDataDto>>, Map<Finger, List<GloveDataDto>>>  {

    @Override
    public Map<Finger, List<GloveDataDto>> apply(Map<Finger, List<GloveDataDto>> rawLeftHandDataByFinger) {
        double[] thumbFingerRawData = rawLeftHandDataByFinger.get(THUMB).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] indexFingerRawData = rawLeftHandDataByFinger.get(INDEX).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] middleFingerRawData = rawLeftHandDataByFinger.get(MIDDLE).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] ringFingerRawData = rawLeftHandDataByFinger.get(RING).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] littleFingerRawData = rawLeftHandDataByFinger.get(LITTLE).stream().mapToDouble(GloveDataDto::getRaw).toArray();

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

        //TODO: bardziej wizualizacja -
        String s = new CsvBeanParser().parseToCsv(res, MainController.getDestinationSubFolder() + "/rekawica_prawa.csv");
        //save

        return rawLeftHandDataByFinger;
    }
}
