package org.politechnika.report.impl.glove_functions;

import org.politechnika.data_parser.CsvToBeanParser;
import org.politechnika.data_parser.model.GloveDataDto;
import org.politechnika.data_parser.strategy.GloveParsingStrategy;
import org.politechnika.file.AbstractDataFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Function;

public class ParseToBeans implements Function<AbstractDataFile, List<GloveDataDto>> {

    @Override
    public List<GloveDataDto> apply(AbstractDataFile abstractDataFile) {
        CsvToBeanParser csvToBeanParser = new CsvToBeanParser();

        try {
            return csvToBeanParser.parseToBean(abstractDataFile, new GloveParsingStrategy());
        } catch (FileNotFoundException e) {
            //TODO: do something -> print error to user
            throw new IllegalStateException(e);
        }
    }
}
