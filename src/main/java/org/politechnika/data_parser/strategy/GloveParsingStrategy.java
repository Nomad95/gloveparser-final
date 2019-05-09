package org.politechnika.data_parser.strategy;

import org.politechnika.data_parser.model.GloveDataDto;

import static org.politechnika.commons.Separators.PIPE;
import static org.politechnika.commons.Separators.SEMICOLON;

public class GloveParsingStrategy implements ParsingStrategy<GloveDataDto> {

    @Override
    public Class<GloveDataDto> getBeanClass() {
        return GloveDataDto.class;
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
