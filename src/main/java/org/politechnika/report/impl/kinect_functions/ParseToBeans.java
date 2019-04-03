package org.politechnika.report.impl.kinect_functions;

import org.politechnika.data_parser.csv.definitions.GloveParsingStrategy;
import org.politechnika.data_parser.csv.definitions.KinectParsingStrategy;
import org.politechnika.data_parser.csv.definitions.beans.KinectDataDto;
import org.politechnika.data_parser.csv.impl.BeanCsvParser;
import org.politechnika.file.model.AbstractDataFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Function;

public class ParseToBeans implements Function<AbstractDataFile, List<KinectDataDto>> {

    @Override
    public List<KinectDataDto> apply(AbstractDataFile abstractDataFile) {
        BeanCsvParser beanCsvParser = new BeanCsvParser();

        try {
            return beanCsvParser.parseToBean(abstractDataFile, new KinectParsingStrategy());
        } catch (FileNotFoundException e) {
            //TODO: do something -> print error to user
            throw new IllegalStateException(e);
        }
    }
}
