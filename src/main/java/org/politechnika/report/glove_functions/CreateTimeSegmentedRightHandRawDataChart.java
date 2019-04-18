package org.politechnika.report.glove_functions;

import org.politechnika.commons.Constants;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.frontend.MainController;
import org.politechnika.matlab.ChartGeneratorImpl;
import org.politechnika.matlab.builders.Plot;
import org.politechnika.model.glove.Finger;
import org.politechnika.processing.DoubleArrayTimeSeries;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.politechnika.model.glove.Finger.*;
import static org.politechnika.processing.DoubleArrayTimeSeries.AligningMode.LAST_VALUE;

public class CreateTimeSegmentedRightHandRawDataChart implements Function<Map<Finger, List<GloveDataDto>>, Map<Finger, List<GloveDataDto>>> {

    @Override
    public Map<Finger, List<GloveDataDto>> apply(Map<Finger, List<GloveDataDto>> rawRightHandDataByFinger) {
        double[] thumbFingerRawData = rawRightHandDataByFinger.get(THUMB).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] indexFingerRawData = rawRightHandDataByFinger.get(INDEX).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] middleFingerRawData = rawRightHandDataByFinger.get(MIDDLE).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] ringFingerRawData = rawRightHandDataByFinger.get(RING).stream().mapToDouble(GloveDataDto::getRaw).toArray();
        double[] littleFingerRawData = rawRightHandDataByFinger.get(LITTLE).stream().mapToDouble(GloveDataDto::getRaw).toArray();

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
