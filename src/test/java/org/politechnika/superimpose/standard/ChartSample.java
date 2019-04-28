package org.politechnika.superimpose.standard;

import javafx.application.Application;
import javafx.stage.Stage;
import org.politechnika.model.glove.GloveValueDto;
import org.politechnika.model.kinect.PointDistanceValueDto;
import org.politechnika.model.pulsometer.PulsometerValueDto;

import java.time.Instant;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

public class ChartSample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer);
        Instant now = Instant.now();
        LinkedList<GloveValueDto> left =
                IntStream.generate(new IncrementalIntSupplier(400))
                        .mapToObj(i -> newGloveValueOfTime(now.plusMillis(i)))
                        .limit(60)
                        .collect(toCollection(LinkedList::new));
        LinkedList<GloveValueDto> right =
                IntStream.generate(new IncrementalIntSupplier(400))
                        .mapToObj(i -> newGloveValueOfTime(now.plusMillis(i)))
                        .limit(60)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PointDistanceValueDto> kinect =
                IntStream.generate(new IncrementalIntSupplier(100))
                        .mapToObj(i -> newKinectValueOfTime(now.plusMillis(i)))
                        .limit(210)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PulsometerValueDto> puls =
                IntStream.generate(new IncrementalIntSupplier(1000))
                        .mapToObj(i -> newPulsValueOfTime(now.plusMillis(i)))
                        .limit(22)
                        .collect(toCollection(LinkedList::new));
        stdSuper.loadLeftGloveValues(left);
        stdSuper.loadRightGloveValues(right);
        stdSuper.loadKinectValues(kinect);
        stdSuper.loadPulsometerValues(puls);
        stdSuper.adjustSeries();

        //when
        //getChartBundle();

        //then should draw chart
    }

    private GloveValueDto newGloveValueOfTime(Instant time) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return new GloveValueDto(
                rand.nextDouble(400, 2000),
                rand.nextDouble(400, 2000),
                rand.nextDouble(400, 2000),
                rand.nextDouble(400, 2000),
                rand.nextDouble(400, 2000),
                time);
    }

    private PointDistanceValueDto newKinectValueOfTime(Instant time) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return new PointDistanceValueDto(
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                rand.nextDouble(0, 500),
                time);
    }

    private PulsometerValueDto newPulsValueOfTime(Instant time) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        return new PulsometerValueDto(time, rand.nextInt(0, 100));
    }
}
