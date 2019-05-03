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
public class CreateTimeSegmentedRightHandRawDataChart implements UnaryOperator<Map<Finger, List<GloveDataDto>>> {

    @Override
    public Map<Finger, List<GloveDataDto>> apply(Map<Finger, List<GloveDataDto>> rawRightHandDataByFinger) {
        DoubleArrayTimeSeries arrayTimeSeries = new AlignGloveSeries().apply(rawRightHandDataByFinger);

        drawChart(
                arrayTimeSeries.getAllSeriesArrays(),
                arrayTimeSeries.getSequencedTimeArray()
        );

        return rawRightHandDataByFinger;
    }

    private void drawChart(Object[] dataSeries, double[] timeSeries) {
        try {
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
        } catch (ParserMatlabException e) {
            log.error("Could not create Time segmented right hand raw data chart: {}", e.getMessage());
        }
    }
}
