package org.politechnika.report.pulsometer_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.IllegalParserStateException;
import org.politechnika.data_parser.CsvToBeanParser;
import org.politechnika.data_parser.model.PulsometerDataDto;
import org.politechnika.data_parser.strategy.PulsometerParsingStrategy;
import org.politechnika.file.AbstractDataFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class ParsePulsometerData implements Function<AbstractDataFile, List<PulsometerDataDto>> {

    @Override
    public List<PulsometerDataDto> apply(AbstractDataFile dataFile) {
        CsvToBeanParser csvToBeanParser = new CsvToBeanParser();
        try {
            log.debug("Parsing pulsometer data...");
            return csvToBeanParser.parseToBean(dataFile, new PulsometerParsingStrategy());
        } catch (FileNotFoundException e) {
            //TODO: do something -> print error to user
            throw new IllegalParserStateException("Could not parse glove data file!", e);
        }
    }
}
