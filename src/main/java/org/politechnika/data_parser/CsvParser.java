package org.politechnika.data_parser;

import org.politechnika.data_parser.strategy.ParsingStrategy;
import org.politechnika.file.AbstractDataFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface CsvParser {

    <T> List<T> parseToBean(AbstractDataFile file, ParsingStrategy<T> parsingStrategy) throws FileNotFoundException;
}
