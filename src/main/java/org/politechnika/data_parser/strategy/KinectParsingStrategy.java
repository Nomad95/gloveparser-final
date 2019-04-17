package org.politechnika.data_parser.strategy;

import org.politechnika.data_parser.model.KinectDataDto;

import static org.politechnika.commons.Separators.PIPE;
import static org.politechnika.commons.Separators.SEMICOLON;

public class KinectParsingStrategy implements ParsingStrategy<KinectDataDto> {

    @Override
    public Class<KinectDataDto> getBeanClass() {
        return KinectDataDto.class;
    }

    @Override
    public char getReadSeparator() {
        return PIPE;
    }

    @Override
    public char getWriteSeparator() {
        return SEMICOLON;
    }
}
