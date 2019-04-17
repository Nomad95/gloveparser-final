package org.politechnika.report.impl.kinect_functions;

import org.politechnika.data_parser.CsvToBeanParser;
import org.politechnika.data_parser.model.KinectDataDto;
import org.politechnika.data_parser.strategy.KinectParsingStrategy;
import org.politechnika.file.model.AbstractDataFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Function;

public class ParseToBeans implements Function<AbstractDataFile, List<KinectDataDto>> {

    @Override
    public List<KinectDataDto> apply(AbstractDataFile abstractDataFile) {
        CsvToBeanParser csvToBeanParser = new CsvToBeanParser();

        try {
            return csvToBeanParser.parseToBean(abstractDataFile, new KinectParsingStrategy());
        } catch (FileNotFoundException e) {
            //TODO: do something -> print error to user
            throw new IllegalStateException(e);
        }
    }
}
