package org.politechnika.report.pulsometer_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.IllegalParserStateException;
import org.politechnika.data_parser.CsvToBeanParser;
import org.politechnika.data_parser.model.PulsometerDataDto;
import org.politechnika.data_parser.model.TimestampPulsometerData;
import org.politechnika.data_parser.strategy.CsvPulsometerParsingStrategy;
import org.politechnika.data_parser.strategy.TxtPulsometerParsingStrategy;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.frontend.Alerts;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.politechnika.frontend.Alerts.FILE_NOT_FOUND;
import static org.politechnika.frontend.Alerts.PULSOMETER_PARSING_ERR;

@Slf4j
public class ParsePulsometerData implements Function<AbstractDataFile, List<PulsometerDataDto>> {

    @Override
    public List<PulsometerDataDto> apply(AbstractDataFile dataFile) {
        CsvToBeanParser csvToBeanParser = new CsvToBeanParser();
        try {
            log.debug("Parsing pulsometer data...");
            if ("txt".equals(dataFile.getFileExtension()))
                return csvToBeanParser.parseToBean(dataFile, new TxtPulsometerParsingStrategy());
            else
                return csvToBeanParser.parseToBean(dataFile, new CsvPulsometerParsingStrategy())
                        .stream()
                        .map(TimestampPulsometerData::toPulsometerDto)
                        .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            Alerts.I.raiseError(FILE_NOT_FOUND + dataFile.getFilePath());
            throw new IllegalParserStateException("Could not parse glove data file!", e);
        } catch (RuntimeException e) {
            log.error("Invalid pulsometer file");
            Alerts.I.raiseError(PULSOMETER_PARSING_ERR);
            throw e;
        }
    }
}
