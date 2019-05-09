package org.politechnika.data_parser.strategy;

import org.politechnika.data_parser.model.PulsometerDataDto;

import static org.politechnika.commons.Separators.SEMICOLON;
import static org.politechnika.commons.Separators.SPACE;

public class TxtPulsometerParsingStrategy implements ParsingStrategy<PulsometerDataDto> {

    @Override
    public Class<PulsometerDataDto> getBeanClass() {
        return PulsometerDataDto.class;
    }

    @Override
    public char getReadSeparator() {
        return SPACE;
    }

    @Override
    public char getWriteSeparator() {
        return SEMICOLON;
    }

    @Override
    public boolean supportsFileExtension(String ext) {
        return "txt".equals(ext);
    }
}
