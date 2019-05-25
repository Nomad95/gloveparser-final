package org.politechnika.report.glove_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.IllegalParserStateException;
import org.politechnika.data_parser.CsvToBeanParser;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.data_parser.strategy.GloveParsingStrategy;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.frontend.Alerts;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Function;

import static org.politechnika.frontend.Alerts.FILE_NOT_FOUND;
import static org.politechnika.frontend.Alerts.GLOVE_PARSING_ERR;

@Slf4j
public class ParseToBeans implements Function<AbstractDataFile, List<GloveDataDto>> {

    @Override
    public List<GloveDataDto> apply(AbstractDataFile abstractDataFile) {
        CsvToBeanParser csvToBeanParser = new CsvToBeanParser();

        try {
            return csvToBeanParser.parseToBean(abstractDataFile, new GloveParsingStrategy());
        } catch (FileNotFoundException e) {
            Alerts.I.raiseError(FILE_NOT_FOUND + abstractDataFile.getFilePath());
            throw new IllegalParserStateException("Could not find glove data file!", e);
        } catch (RuntimeException e) {
            log.error("Invalid glove file");
            Alerts.I.raiseError(GLOVE_PARSING_ERR);
            throw e;
        }
    }
}
