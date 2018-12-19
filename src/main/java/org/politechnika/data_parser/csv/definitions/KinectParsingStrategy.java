package org.politechnika.data_parser.csv.definitions;

import org.politechnika.data_parser.csv.ParsingStrategy;
import org.politechnika.data_parser.csv.definitions.beans.KinectDataDto;

import static org.politechnika.commons.Separators.PIPE;

public class KinectParsingStrategy implements ParsingStrategy<KinectDataDto> {

    @Override
    public Class<KinectDataDto> getBeanClass() {
        return KinectDataDto.class;
    }

    @Override
    public char getSeparator() {
        return PIPE;
    }
}
