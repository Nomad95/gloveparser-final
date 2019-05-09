package org.politechnika.data_parser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.politechnika.StaticTestResources;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.data_parser.model.PulsometerDataDto;
import org.politechnika.data_parser.strategy.GloveParsingStrategy;
import org.politechnika.data_parser.strategy.TxtPulsometerParsingStrategy;
import org.politechnika.file.AbstractDataFile;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BeanParserUnitTest {

    @Mock
    private AbstractDataFile file;

    @Test
    public void shouldParseGenerics() throws FileNotFoundException {
        Mockito.doReturn(new StringReader(StaticTestResources.GLOVE_TEST_DATA)).when(file).getReader();

        CsvToBeanParser csvToBeanParser = new CsvToBeanParser();
        List<GloveDataDto> gloveDataFiles = csvToBeanParser.parseToBean(file, new GloveParsingStrategy());
    }

    @Test
    public void shouldParsePulsometerData() throws FileNotFoundException {
        Mockito.doReturn(new StringReader(StaticTestResources.PULSOMETER_TEST_DATA)).when(file).getReader();

        CsvToBeanParser csvToBeanParser = new CsvToBeanParser();
        List<PulsometerDataDto> pulsometerDataFiles = csvToBeanParser.parseToBean(file, new TxtPulsometerParsingStrategy());
    }
}
