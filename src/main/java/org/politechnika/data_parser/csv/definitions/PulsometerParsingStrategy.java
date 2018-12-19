package org.politechnika.data_parser.csv.definitions;

import org.politechnika.data_parser.csv.ParsingStrategy;
import org.politechnika.data_parser.csv.definitions.beans.PulsometerDataDto;

import static org.politechnika.commons.Separators.SPACE;

public class PulsometerParsingStrategy implements ParsingStrategy<PulsometerDataDto> {

    @Override
    public Class<PulsometerDataDto> getBeanClass() {
        return PulsometerDataDto.class;
    }

    @Override
    public char getSeparator() {
        return SPACE;
    }
}
