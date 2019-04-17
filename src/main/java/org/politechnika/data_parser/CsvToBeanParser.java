package org.politechnika.data_parser;

import com.opencsv.bean.CsvToBeanBuilder;
import org.politechnika.data_parser.strategy.ParsingStrategy;
import org.politechnika.file.model.AbstractDataFile;

import java.io.FileNotFoundException;
import java.util.List;

public class CsvToBeanParser implements CsvParser {

    @Override
    public <T> List<T> parseToBean(AbstractDataFile file, ParsingStrategy<T> parsingStrategy)
            throws FileNotFoundException {
        CsvToBeanBuilder<T> builder = new CsvToBeanBuilder<>(file.getReader());
        builder.withSeparator(parsingStrategy.getReadSeparator());
        builder.withType(parsingStrategy.getBeanClass());

        return builder.build().parse();
    }
}
