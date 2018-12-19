package org.politechnika.data_parser.csv;

import org.politechnika.file.model.AbstractDataFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface CsvParser {

    <T> List<T> parseToBean(AbstractDataFile file, ParsingStrategy<T> parsingStrategy) throws FileNotFoundException;
}
