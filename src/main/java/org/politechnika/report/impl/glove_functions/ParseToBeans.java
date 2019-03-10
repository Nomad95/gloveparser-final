package org.politechnika.report.impl.glove_functions;

import org.politechnika.data_parser.csv.definitions.GloveParsingStrategy;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;
import org.politechnika.data_parser.csv.impl.BeanCsvParser;
import org.politechnika.file.model.AbstractDataFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Function;

public class ParseToBeans implements Function<AbstractDataFile, List<GloveDataDto>> {

    @Override
    public List<GloveDataDto> apply(AbstractDataFile abstractDataFile) {
        BeanCsvParser beanCsvParser = new BeanCsvParser();

        try {
            return beanCsvParser.parseToBean(abstractDataFile, new GloveParsingStrategy());
        } catch (FileNotFoundException e) {
            //TODO: do something -> print error to user
            throw new IllegalStateException(e);
        }
    }
}
