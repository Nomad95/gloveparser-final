package org.politechnika.report;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.Constants;
import org.politechnika.data_parser.model.PulsometerDataDto;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.report.pulsometer_functions.*;

import java.util.List;

@Slf4j
public class PulsometerReportGenerator implements ReportGenerator {

    @Override
    public void generate(AbstractDataFile dataFile) {
        log.debug("Generating pulsometer report");
        List<PulsometerDataDto> pulsometerDataDtos = new ParsePulsometerData().apply(dataFile);

        new CalculatePulsometerStatistics()
                .andThen(new CacheStatistics())
                .apply(pulsometerDataDtos);
        new GeneratePulsChart()
                .andThen(new StorePulsometerValues())
                .apply(pulsometerDataDtos);

        log.debug("Pulsometer report was generated");
    }

    @Override
    public boolean supports(String fileType) {
        return Constants.PULSOMETER.equals(fileType);
    }

}
