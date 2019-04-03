package org.politechnika.report_generator;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.politechnika.StaticTestResources;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.report.impl.PulsometerReportGenerator;

import java.io.FileNotFoundException;
import java.io.StringReader;

@RunWith(MockitoJUnitRunner.class)
public class PulsometerReportGeneratorUnitTest {

    @Mock
    private AbstractDataFile file;

    private PulsometerReportGenerator reportGenerator;

    @Before
    public void setUp() {
        reportGenerator = new PulsometerReportGenerator();
    }

    @Test
    public void shouldGenerateReport() throws FileNotFoundException {
        Mockito.doReturn(new StringReader(StaticTestResources.PULSOMETER_TEST_DATA)).when(file).getReader();

        reportGenerator.generate(file);
    }
}
