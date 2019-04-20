package org.politechnika.report.glove_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.Constants;
import org.politechnika.commons.ParserMatlabException;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.glove.Finger;
import org.politechnika.processing.DoubleArrayTimeSeries;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import static org.politechnika.model.glove.Finger.*;
import static org.politechnika.processing.DoubleArrayTimeSeries.AligningMode.LAST_VALUE;

@Slf4j
public class CreateTimeSegmentedLeftHandRawDataChart implements UnaryOperator<Map<Finger, List<GloveDataDto>>> {

    @Override
    public Map<Finger, List<GloveDataDto>> apply(Map<Finger, List<GloveDataDto>> rawLeftHandDataByFinger) {
        log.debug("Creating raw data chart for left hand");
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

        drawChart(
                arrayTimeSeries.getAllSeriesArrays(),
                arrayTimeSeries.getSequencedTimeArray()
        );

        log.debug("Chart generated");
        return rawLeftHandDataByFinger;
    }

    private void drawChart(Object[] dataSeries, double[] timeSeries) {
        try {
            new ChartGeneratorImpl().drawChart(
                    new Plot.Builder(
                            dataSeries,
                            timeSeries)
                            .withFileName("left_hand_raw_data")
                            .withGrid()
                            .withLegend("{'Kciuk','Wskazujący', 'Środkowy', 'Serdeczny', 'Mały'}")
                            .withTitle("Wartości pobrane z poszczególnych palców lewej ręki")
                            .withXAxisName("Numer wartości")
                            .withYAxisName("Wartości pobrane z rękawicy")
                            .build(MainController.getDestinationSubFolder()));
        } catch (ParserMatlabException e) {
            log.error("COuld not create Time segmented left hand raw data chart");
        }
    }
}
