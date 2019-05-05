package org.politechnika.report.glove_functions;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class CreateTimeSegmentedLeftHandRawDataChart implements UnaryOperator<Map<Finger, List<GloveDataDto>>> {

    @Override
    public Map<Finger, List<GloveDataDto>> apply(Map<Finger, List<GloveDataDto>> rawLeftHandDataByFinger) {
        log.debug("Creating raw data chart for left hand");
        DoubleArrayTimeSeries arrayTimeSeries = new AlignGloveSeries().apply(rawLeftHandDataByFinger);

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
