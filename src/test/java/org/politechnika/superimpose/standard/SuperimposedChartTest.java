package org.politechnika.superimpose.standard;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.politechnika.StaticTestResources;
import org.politechnika.data_parser.CsvToBeanParser;
import org.politechnika.data_parser.model.PulsometerDataDto;
import org.politechnika.data_parser.strategy.TxtPulsometerParsingStrategy;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.model.glove.GloveValueDto;
import org.politechnika.model.kinect.PointDistanceValueDto;
import org.politechnika.model.pulsometer.PulsometerValueDto;
import org.politechnika.superimpose.Projection;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(MockitoJUnitRunner.class)
public class SuperimposedChartTest {


    @Mock
    private AbstractDataFile file;
    private StandardSuperimposedChartFactory factory;

    @Before
    public void setup() {
        factory = new StandardSuperimposedChartFactory();
    }

    @Test
    public void shouldAddValues() {
        //given
        ArrayList<GloveValueDto> left = Lists.newArrayList(newGloveValueOfTime(Instant.now()));
        ArrayList<GloveValueDto> right = Lists.newArrayList(newGloveValueOfTime(Instant.now()));
        ArrayList<PulsometerValueDto> puls = Lists.newArrayList(newPulsValueOfTime(Instant.now()));
        ArrayList<PointDistanceValueDto> kinect = Lists.newArrayList(newKinectValueOfTime(Instant.now()));

        //when
        StandardSuperimposedChart superimposed = (StandardSuperimposedChart) factory.getSuperimposedChartGenerator();
        superimposed.loadKinectValues(kinect);
        superimposed.loadLeftGloveValues(left);
        superimposed.loadRightGloveValues(right);
        superimposed.loadPulsometerValues(puls);

        //then
        Assert.assertFalse(superimposed.leftGloveValues.isEmpty());
        Assert.assertFalse(superimposed.rightGloveValues.isEmpty());
        Assert.assertFalse(superimposed.pulsometerValues.isEmpty());
        Assert.assertFalse(superimposed.kinectValues.isEmpty());
    }

    @Test
    public void shouldFindSeriesWithMostFrequentTimeChanges() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
        LinkedList<GloveValueDto> left =
                IntStream.generate(new IncrementalIntSupplier(5))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<GloveValueDto> right =
                IntStream.generate(new IncrementalIntSupplier(5))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PulsometerValueDto> puls =
                IntStream.generate(new IncrementalIntSupplier(2))
                        .mapToObj(i -> newPulsValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PointDistanceValueDto> kinect =
                IntStream.generate(new IncrementalIntSupplier(2))
                        .mapToObj(i -> newKinectValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        stdSuper.loadKinectValues(kinect);
        stdSuper.loadLeftGloveValues(left);
        stdSuper.loadRightGloveValues(right);
        stdSuper.loadPulsometerValues(puls);

        //when
        SeriesType mostFreq = transformer.findSeriesWithMostFrequentChanges(stdSuper);

        //then
        Assert.assertEquals(SeriesType.KINECT, mostFreq);
    }

    @Test
    public void shouldFindSeriesWithMostFrequentTimeChangesWhenSeriesAreUnequal() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
        LinkedList<GloveValueDto> left =
                IntStream.generate(new IncrementalIntSupplier(5))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(15)
                        .collect(toCollection(LinkedList::new));
        LinkedList<GloveValueDto> right =
                IntStream.generate(new IncrementalIntSupplier(3))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PulsometerValueDto> puls =
                IntStream.generate(new IncrementalIntSupplier(4))
                        .mapToObj(i -> newPulsValueOfTime(Instant.now().plusMillis(i)))
                        .limit(22)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PointDistanceValueDto> kinect =
                IntStream.generate(new IncrementalIntSupplier(6))
                        .mapToObj(i -> newKinectValueOfTime(Instant.now().plusMillis(i)))
                        .limit(21)
                        .collect(toCollection(LinkedList::new));
        stdSuper.loadKinectValues(kinect);
        stdSuper.loadLeftGloveValues(left);
        stdSuper.loadRightGloveValues(right);
        stdSuper.loadPulsometerValues(puls);

        //when
        SeriesType mostFreq = transformer.findSeriesWithMostFrequentChanges(stdSuper);

        //then
        Assert.assertEquals(SeriesType.RIGHT_HAND, mostFreq);
    }

    @Test
    public void shouldFindSeriesWithMostFrequentTimeChangesWhenOneSeriesAreEmpty() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
        LinkedList<GloveValueDto> left = new LinkedList<>();
        LinkedList<GloveValueDto> right =
                IntStream.generate(new IncrementalIntSupplier(3))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PulsometerValueDto> puls =
                IntStream.generate(new IncrementalIntSupplier(4))
                        .mapToObj(i -> newPulsValueOfTime(Instant.now().plusMillis(i)))
                        .limit(22)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PointDistanceValueDto> kinect =
                IntStream.generate(new IncrementalIntSupplier(6))
                        .mapToObj(i -> newKinectValueOfTime(Instant.now().plusMillis(i)))
                        .limit(21)
                        .collect(toCollection(LinkedList::new));
        stdSuper.loadKinectValues(kinect);
        stdSuper.loadLeftGloveValues(left);
        stdSuper.loadRightGloveValues(right);
        stdSuper.loadPulsometerValues(puls);

        //when
        SeriesType mostFreq = transformer.findSeriesWithMostFrequentChanges(stdSuper);

        //then
        Assert.assertEquals(SeriesType.RIGHT_HAND, mostFreq);
    }

    @Test
    public void shouldFindSeriesWithMostFrequentTimeChangesWhenTHeyAreUnequalAndEmptyCase2() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
        LinkedList<GloveValueDto> left = new LinkedList<>();
        LinkedList<GloveValueDto> right =
                IntStream.generate(new IncrementalIntSupplier(4))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PulsometerValueDto> puls =
                IntStream.generate(new IncrementalIntSupplier(3))
                        .mapToObj(i -> newPulsValueOfTime(Instant.now().plusMillis(i)))
                        .limit(22)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PointDistanceValueDto> kinect =
                IntStream.generate(new IncrementalIntSupplier(5))
                        .mapToObj(i -> newKinectValueOfTime(Instant.now().plusMillis(i)))
                        .limit(21)
                        .collect(toCollection(LinkedList::new));
        stdSuper.loadKinectValues(kinect);
        stdSuper.loadLeftGloveValues(left);
        stdSuper.loadRightGloveValues(right);
        stdSuper.loadPulsometerValues(puls);

        //when
        SeriesType mostFreq = transformer.findSeriesWithMostFrequentChanges(stdSuper);

        //then
        Assert.assertEquals(SeriesType.PULS, mostFreq);
    }

    @Test
    public void shouldFindSeriesWithMostFrequentTimeChangesWhenTHeyAreUnequalAndEmptyCase3() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
        LinkedList<GloveValueDto> left =
                IntStream.generate(new IncrementalIntSupplier(10))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<GloveValueDto> right =
                IntStream.generate(new IncrementalIntSupplier(3))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PulsometerValueDto> puls =
                IntStream.generate(new IncrementalIntSupplier(9))
                        .mapToObj(i -> newPulsValueOfTime(Instant.now().plusMillis(i)))
                        .limit(22)
                        .collect(toCollection(LinkedList::new));
        stdSuper.loadLeftGloveValues(left);
        stdSuper.loadRightGloveValues(right);
        stdSuper.loadPulsometerValues(puls);

        //when
        SeriesType mostFreq = transformer.findSeriesWithMostFrequentChanges(stdSuper);

        //then
        Assert.assertEquals(SeriesType.RIGHT_HAND, mostFreq);
    }

    @Test
    public void shouldFindSeriesWithMostFrequentTimeChangesWhenTimeZeroInTwoCollections() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
        LinkedList<GloveValueDto> left =
                IntStream.generate(new IncrementalIntSupplier(0))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<GloveValueDto> right =
                IntStream.generate(new IncrementalIntSupplier(15))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PulsometerValueDto> puls =
                IntStream.generate(new IncrementalIntSupplier(0))
                        .mapToObj(i -> newPulsValueOfTime(Instant.now().plusMillis(i)))
                        .limit(22)
                        .collect(toCollection(LinkedList::new));
        stdSuper.loadLeftGloveValues(left);
        stdSuper.loadRightGloveValues(right);
        stdSuper.loadPulsometerValues(puls);

        //when
        SeriesType mostFreq = transformer.findSeriesWithMostFrequentChanges(stdSuper);

        //then
        Assert.assertEquals(SeriesType.PULS, mostFreq); //series has constant time but qualifies as 'the most frequent time change'
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowWhenAllSeriesAreEmpty() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);

        //when
        transformer.findSeriesWithMostFrequentChanges(stdSuper);
    }

    @Test
    public void shouldAlignTimeOfTheRestOfTheSeriesToPulsWhenSeriesAreBeforePuls() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
        LinkedList<GloveValueDto> left =
                IntStream.generate(new IncrementalIntSupplier(400))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i + 10000)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PointDistanceValueDto> kinect =
                IntStream.generate(new IncrementalIntSupplier(100))
                        .mapToObj(i -> newKinectValueOfTime(Instant.now().plusMillis(i + 10000)))
                        .limit(21)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PulsometerValueDto> puls =
                IntStream.generate(new IncrementalIntSupplier(1000))
                        .mapToObj(i -> newPulsValueOfTime(Instant.now().plusMillis(i)))
                        .limit(22)
                        .collect(toCollection(LinkedList::new));
        stdSuper.loadLeftGloveValues(left);
        stdSuper.loadKinectValues(kinect);
        stdSuper.loadPulsometerValues(puls);

        //when
        seriesTransformer.transformSeriesToStartAtSameTimeAs(stdSuper, SeriesType.PULS);

        //then
        Assert.assertEquals(stdSuper.pulsometerValues.get(0).getTime(), stdSuper.kinectValues.get(0).getTime());
        Assert.assertEquals(stdSuper.pulsometerValues.get(0).getTime(), stdSuper.leftGloveValues.get(0).getTime());
    }

    @Test
    public void shouldAlignTimeOfTheRestOfTheSeriesToPulsWhenSeriesAreAfterPuls() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
        LinkedList<GloveValueDto> left =
                IntStream.generate(new IncrementalIntSupplier(400))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PointDistanceValueDto> kinect =
                IntStream.generate(new IncrementalIntSupplier(100))
                        .mapToObj(i -> newKinectValueOfTime(Instant.now().plusMillis(i)))
                        .limit(21)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PulsometerValueDto> puls =
                IntStream.generate(new IncrementalIntSupplier(1000))
                        .mapToObj(i -> newPulsValueOfTime(Instant.now().plusMillis(i)))
                        .limit(22)
                        .collect(toCollection(LinkedList::new));
        stdSuper.loadLeftGloveValues(left);
        stdSuper.loadKinectValues(kinect);
        stdSuper.loadPulsometerValues(puls);

        //when
        seriesTransformer.transformSeriesToStartAtSameTimeAs(stdSuper, SeriesType.PULS);

        //then
        Assert.assertEquals(stdSuper.kinectValues.get(0).getTime(), stdSuper.pulsometerValues.get(0).getTime());
        Assert.assertEquals(stdSuper.kinectValues.get(0).getTime(), stdSuper.leftGloveValues.get(0).getTime());
    }

    @Test
    public void shouldAlignTimeOfTheRestOfTheSeriesToEndOfThePulsWhenSeriesAreAfterPuls() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
        LinkedList<GloveValueDto> left =
                IntStream.generate(new IncrementalIntSupplier(400))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PointDistanceValueDto> kinect =
                IntStream.generate(new IncrementalIntSupplier(100))
                        .mapToObj(i -> newKinectValueOfTime(Instant.now().plusMillis(i)))
                        .limit(21)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PulsometerValueDto> puls =
                IntStream.generate(new IncrementalIntSupplier(1000))
                        .mapToObj(i -> newPulsValueOfTime(Instant.now().plusMillis(i)))
                        .limit(22)
                        .collect(toCollection(LinkedList::new));
        stdSuper.loadLeftGloveValues(left);
        stdSuper.loadKinectValues(kinect);
        stdSuper.loadPulsometerValues(puls);

        //when
        seriesTransformer.transformSeriesToEndAtSameTimeAs(stdSuper, SeriesType.PULS);

        //then
        Assert.assertEquals(stdSuper.kinectValues.get(stdSuper.kinectValues.size() -1 ).getTime(), stdSuper.pulsometerValues.get(stdSuper.pulsometerValues.size() - 1 ).getTime());
        Assert.assertEquals(stdSuper.kinectValues.get(stdSuper.kinectValues.size() -1 ).getTime(), stdSuper.leftGloveValues.get(stdSuper.leftGloveValues.size() - 1 ).getTime());
    }

    @Test
    public void shouldCutProvidedSeries() throws FileNotFoundException {
        //given
        Mockito.doReturn(new StringReader(StaticTestResources.PULSOMETER_TEST_DATA)).when(file).getReader();
        CsvToBeanParser csvToBeanParser = new CsvToBeanParser();
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, new AllProjection());
        LinkedList<GloveValueDto> left =
                IntStream.generate(new IncrementalIntSupplier(400))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PointDistanceValueDto> kinect =
                IntStream.generate(new IncrementalIntSupplier(100))
                        .mapToObj(i -> newKinectValueOfTime(Instant.now().plusMillis(i)))
                        .limit(21)
                        .collect(toCollection(LinkedList::new));
        List<PulsometerValueDto> puls = csvToBeanParser.parseToBean(file, new TxtPulsometerParsingStrategy()).stream()
                .map(PulsometerDataDto::toValueDto)
                .collect(toCollection(LinkedList::new));
        stdSuper.loadLeftGloveValues(left);
        stdSuper.loadKinectValues(kinect);
        stdSuper.loadPulsometerValues(puls);

        //when
        seriesTransformer.cutPulsometerValues(stdSuper, val -> val < 0);

        //then
        Assert.assertFalse(
                "Some values are still 0",
                stdSuper.pulsometerValues.stream().anyMatch(val -> val.getValue() == 0));
    }

    @Test
    public void shouldCutTimeOfTheRestOfSeriesToHaveSameTimeLength() throws FileNotFoundException {
        //given
        Mockito.doReturn(new StringReader(StaticTestResources.PULSOMETER_TEST_DATA)).when(file).getReader();
        CsvToBeanParser csvToBeanParser = new CsvToBeanParser();

        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
        LinkedList<GloveValueDto> left =
                IntStream.generate(new IncrementalIntSupplier(400))
                        .mapToObj(i -> newGloveValueOfTime(Instant.now().plusMillis(i)))
                        .limit(20)
                        .collect(toCollection(LinkedList::new));
        LinkedList<PointDistanceValueDto> kinect =
                IntStream.generate(new IncrementalIntSupplier(100))
                        .mapToObj(i -> newKinectValueOfTime(Instant.now().plusMillis(i)))
                        .limit(21)
                        .collect(toCollection(LinkedList::new));
        List<PulsometerValueDto> puls = csvToBeanParser.parseToBean(file, new TxtPulsometerParsingStrategy()).stream()
                .map(PulsometerDataDto::toValueDto)
                .collect(toCollection(LinkedList::new));
        stdSuper.loadLeftGloveValues(left);
        stdSuper.loadKinectValues(kinect);
        stdSuper.loadPulsometerValues(puls);

        //when
        seriesTransformer.cutTimeOfOtherSeriesToAlignToSeriesOfType(stdSuper, SeriesType.PULS);

        //then
        Instant start = stdSuper.pulsometerStream().findFirst().orElseThrow(IllegalStateException::new).getTime();
        Instant end = stdSuper.pulsometerValues.get(stdSuper.pulsometerValues.size() - 1).getTime();

        List<Instant> kinectTimeSeries = stdSuper.kinectStream()
                .map(PointDistanceValueDto::getTime)
                .collect(Collectors.toList());
        Assert.assertThat(kinectTimeSeries, allOf(everyItem(lessThan(end)), everyItem(greaterThan(start))));

        List<Instant> leftHandTimeSeries = stdSuper.leftGloveStream()
                .map(GloveValueDto::getTime)
                .collect(Collectors.toList());
        Assert.assertThat(leftHandTimeSeries , allOf(everyItem(lessThan(end)), everyItem(greaterThan(start))));
    }

    @Test
    public void shouldInterpolateAllValuesForKinect() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
        Instant now = Instant.now();
        LinkedList<GloveValueDto> left =
                IntStream.generate(new IncrementalIntSupplier(400))
                        .mapToObj(i -> newGloveValueOfTime(now.plusMillis(i)))
                        .limit(20)
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
        stdSuper.loadKinectValues(kinect);
        stdSuper.loadPulsometerValues(puls);
        stdSuper.adjustSeries();

        //when
        seriesTransformer.interpolateWithSeries(stdSuper, SeriesType.KINECT);

        //then values were interpolated
        Instant someTime = stdSuper.kinectValues.get(25).getTime();
        Optional<PulsometerValueDto> interpolatedPulsometer = stdSuper.pulsometerValues.stream().filter(v -> v.getTime().equals(someTime)).findFirst();
        Optional<GloveValueDto> interpolatedLeftHand = stdSuper.leftGloveValues.stream().filter(v -> v.getTime().equals(someTime)).findFirst();
        Assert.assertTrue(interpolatedPulsometer.isPresent());
        Assert.assertTrue(interpolatedLeftHand.isPresent());
    }

    @Test
    public void shouldInterpolateAllValuesForPuls() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
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
        seriesTransformer.interpolateWithSeries(stdSuper, SeriesType.PULS);

        //then values were interpolated
        Instant someTime = stdSuper.pulsometerValues.get(5).getTime();
        Optional<PointDistanceValueDto> interpolatedKinect = stdSuper.kinectValues.stream().filter(v -> v.getTime().equals(someTime)).findFirst();
        Optional<GloveValueDto> interpolatedLeftHand = stdSuper.leftGloveValues.stream().filter(v -> v.getTime().equals(someTime)).findFirst();
        Optional<GloveValueDto> interpolatedRightHand = stdSuper.rightGloveValues.stream().filter(v -> v.getTime().equals(someTime)).findFirst();
        Assert.assertTrue(interpolatedRightHand.isPresent());
        Assert.assertTrue(interpolatedKinect.isPresent());
        Assert.assertTrue(interpolatedLeftHand.isPresent());
    }

    @Test
    public void shouldInterpolateAllValuesForGlove() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        StandardProjection standardProjection = new StandardProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, standardProjection);
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
        seriesTransformer.interpolateWithSeries(stdSuper, SeriesType.LEFT_HAND);

        //then values were interpolated
        Instant someTime = stdSuper.leftGloveValues.get(10).getTime();
        Optional<PointDistanceValueDto> interpolatedKinect = stdSuper.kinectValues.stream().filter(v -> v.getTime().equals(someTime)).findFirst();
        Optional<PulsometerValueDto> interpolatedPuls = stdSuper.pulsometerValues.stream().filter(v -> v.getTime().equals(someTime)).findFirst();
        Optional<GloveValueDto> interpolatedRightHand = stdSuper.rightGloveValues.stream().filter(v -> v.getTime().equals(someTime)).findFirst();
        Assert.assertTrue(interpolatedRightHand.isPresent());
        Assert.assertTrue(interpolatedKinect.isPresent());
        Assert.assertTrue(interpolatedPuls.isPresent());
    }

    @Test
    @Ignore
    public void shouldNotRemoveProperData() {
        //given
        TimeFrequencyAnalyzer transformer = new TimeFrequencyAnalyzer();
        SeriesTransformer seriesTransformer = new SeriesTransformer(new SeriesInterpolator(new DirectLinearInterpolation()));
        Projection allProjection = new AllProjection();
        StandardSuperimposedChart stdSuper = new StandardSuperimposedChart(transformer, seriesTransformer, allProjection);
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

        seriesTransformer.cleanData(stdSuper);

        Assert.assertEquals(60, stdSuper.leftGloveValues.size());
        Assert.assertEquals(60, stdSuper.rightGloveValues.size());
        Assert.assertEquals(210, stdSuper.kinectValues.size());
        Assert.assertEquals(22, stdSuper.pulsometerValues.size());
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
        return new PulsometerValueDto(time, rand.nextInt(-100, 100));
    }
}

class AllProjection implements Projection {

    @Override
    public boolean cutPulsometer() {
        return true;
    }

    @Override
    public boolean startAtSameTime() {
        return true;
    }

    @Override
    public boolean endAtSameTime() {
        return false;
    }

    @Override
    public boolean cutOtherToAlignToPulsometer() {
        return true;
    }

    @Override
    public boolean cleanData() {
        return true;
    }
}
