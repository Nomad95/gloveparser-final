package org.politechnika.data_parser.csv.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import org.politechnika.data_parser.csv.CsvParser;
import org.politechnika.data_parser.csv.ParsingStrategy;
import org.politechnika.file.model.AbstractDataFile;

import java.io.FileNotFoundException;
import java.util.List;

public class BeanCsvParser implements CsvParser {

    @Override
    public <T> List<T> parseToBean(AbstractDataFile file, ParsingStrategy<T> parsingStrategy)
            throws FileNotFoundException {
        CsvToBeanBuilder<T> builder = new CsvToBeanBuilder<>(file.getReader());
        builder.withSeparator(parsingStrategy.getSeparator());
        builder.withType(parsingStrategy.getBeanClass());

        return builder.build().parse();
    }
}
