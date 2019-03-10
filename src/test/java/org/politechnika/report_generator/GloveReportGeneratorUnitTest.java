package org.politechnika.report_generator;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.politechnika.StaticTestResources;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.report.impl.GloveReportGenerator;

import java.io.FileNotFoundException;
import java.io.StringReader;

@RunWith(MockitoJUnitRunner.class)
public class GloveReportGeneratorUnitTest {

    @Mock
    private AbstractDataFile file;

    private GloveReportGenerator reportGenerator;

    @Before
    public void setUp() {
        reportGenerator = new GloveReportGenerator();
    }

    @Test
    public void shouldGenerateReport() throws FileNotFoundException {
        Mockito.doReturn(new StringReader(StaticTestResources.GLOVE_TEST_DATA)).when(file).getReader();

        reportGenerator.generate(file);
    }
}
