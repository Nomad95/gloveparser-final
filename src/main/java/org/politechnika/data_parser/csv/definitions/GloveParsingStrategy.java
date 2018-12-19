package org.politechnika.data_parser.csv.definitions;

import org.politechnika.data_parser.csv.ParsingStrategy;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;

import static org.politechnika.commons.Separators.PIPE;

public class GloveParsingStrategy implements ParsingStrategy<GloveDataDto> {

    @Override
    public Class<GloveDataDto> getBeanClass() {
        return GloveDataDto.class;
    }

    @Override
    public char getSeparator() {
        return PIPE;
    }
}
