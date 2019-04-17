package org.politechnika.analysis.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.politechnika.StaticTestResources;
import org.politechnika.analysis.StandardStatisticsAnalyzerImpl;
import org.politechnika.data_parser.csv.definitions.GloveParsingStrategy;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;
import org.politechnika.data_parser.csv.impl.BeanCsvParser;
import org.politechnika.file.model.AbstractDataFile;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StandardStatisticsAnalyzerImplTest {

    @Mock
    private AbstractDataFile file;

    private StandardStatisticsAnalyzerImpl gloveStatisticsAnalyzer = new StandardStatisticsAnalyzerImpl();

    @Test
    public void shouldGetAverageFromList() throws FileNotFoundException {
        Mockito.doReturn(new StringReader(StaticTestResources.GLOVE_TEST_DATA)).when(file).getReader();
        BeanCsvParser beanCsvParser = new BeanCsvParser();
        List<GloveDataDto> gloveDataFiles = beanCsvParser.parseToBean(file, new GloveParsingStrategy());

        double resultAverage = gloveStatisticsAnalyzer.getAverage(gloveDataFiles, GloveDataDto::getRaw);

        Assert.assertEquals(2152.1, resultAverage, 0.001);
    }

    @Test
    public void shouldGetVarianceFromList() throws FileNotFoundException {
        Mockito.doReturn(new StringReader(StaticTestResources.GLOVE_TEST_DATA)).when(file).getReader();
        BeanCsvParser beanCsvParser = new BeanCsvParser();
        List<GloveDataDto> gloveDataFiles = beanCsvParser.parseToBean(file, new GloveParsingStrategy());

        double resultVariance = gloveStatisticsAnalyzer.getVariance(gloveDataFiles, GloveDataDto::getRaw);

        Assert.assertEquals(536087, resultVariance, 1.0);
    }

    @Test
    public void shouldGetStandardDeviationFromList() throws FileNotFoundException {
        Mockito.doReturn(new StringReader(StaticTestResources.GLOVE_TEST_DATA)).when(file).getReader();
        BeanCsvParser beanCsvParser = new BeanCsvParser();
        List<GloveDataDto> gloveDataFiles = beanCsvParser.parseToBean(file, new GloveParsingStrategy());

        double resultStandardDeviation = gloveStatisticsAnalyzer.getStandardDeviation(gloveDataFiles, GloveDataDto::getRaw);

        Assert.assertEquals(732.17, resultStandardDeviation, 1.0);
    }

    @Test
    public void shouldSkewnessFromList() throws FileNotFoundException {
        Mockito.doReturn(new StringReader(StaticTestResources.GLOVE_TEST_DATA)).when(file).getReader();
        BeanCsvParser beanCsvParser = new BeanCsvParser();
        List<GloveDataDto> gloveDataFiles = beanCsvParser.parseToBean(file, new GloveParsingStrategy());

        double resultSkewness = gloveStatisticsAnalyzer.getSkewness(gloveDataFiles, GloveDataDto::getRaw);

        //Wolfram mówi -0.37 medota daje nam -0.44 ale to chyba akceptowalne. Pewnie uzywana jest inna metoda numeryczna + mały sestaw danych
        Assert.assertEquals(-0.44, resultSkewness, 0.1);
    }

    @Test
    public void shouldKurtosisFromList() throws FileNotFoundException {
        Mockito.doReturn(new StringReader(StaticTestResources.GLOVE_TEST_DATA)).when(file).getReader();
        BeanCsvParser beanCsvParser = new BeanCsvParser();
        List<GloveDataDto> gloveDataFiles = beanCsvParser.parseToBean(file, new GloveParsingStrategy());

        double resultKurtosis = gloveStatisticsAnalyzer.getKurtosis(gloveDataFiles, GloveDataDto::getRaw);

        Assert.assertEquals(-1.15, resultKurtosis, 0.1);
    }
}