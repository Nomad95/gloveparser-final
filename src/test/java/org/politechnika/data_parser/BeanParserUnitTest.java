package org.politechnika.data_parser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.politechnika.StaticTestResources;
import org.politechnika.data_parser.csv.definitions.GloveParsingStrategy;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;
import org.politechnika.data_parser.csv.impl.BeanCsvParser;
import org.politechnika.file.model.AbstractDataFile;

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

        BeanCsvParser beanCsvParser = new BeanCsvParser();
        List<GloveDataDto> gloveDataFiles = beanCsvParser.parseToBean(file, new GloveParsingStrategy());
    }
}
