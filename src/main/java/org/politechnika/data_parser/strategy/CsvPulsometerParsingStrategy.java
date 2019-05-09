package org.politechnika.data_parser.strategy;

import org.politechnika.data_parser.model.TimestampPulsometerData;

import static org.politechnika.commons.Separators.PIPE;
import static org.politechnika.commons.Separators.SEMICOLON;

public class CsvPulsometerParsingStrategy implements ParsingStrategy<TimestampPulsometerData> {

    @Override
    public Class<TimestampPulsometerData> getBeanClass() {
        return TimestampPulsometerData.class;
    }

    @Override
    public char getReadSeparator() {
        return PIPE;
    }

    @Override
    public char getWriteSeparator() {
        return SEMICOLON;
    }

    @Override
    public boolean supportsFileExtension(String ext) {
        return "csv".equals(ext);
    }
}
