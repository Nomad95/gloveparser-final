package org.politechnika.report.kinect_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.data_parser.CsvToBeanParser;
import org.politechnika.data_parser.model.KinectDataDto;
import org.politechnika.data_parser.strategy.KinectParsingStrategy;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.frontend.Alerts;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Function;

import static org.politechnika.frontend.Alerts.FILE_NOT_FOUND;
import static org.politechnika.frontend.Alerts.KINECT_PARSING_ERR;

@Slf4j
public class ParseToBeans implements Function<AbstractDataFile, List<KinectDataDto>> {

    @Override
    public List<KinectDataDto> apply(AbstractDataFile abstractDataFile) {
        CsvToBeanParser csvToBeanParser = new CsvToBeanParser();

        try {
            return csvToBeanParser.parseToBean(abstractDataFile, new KinectParsingStrategy());
        } catch (FileNotFoundException e) {
            Alerts.I.raiseError(FILE_NOT_FOUND + abstractDataFile.getFilePath());
            throw new IllegalStateException(e);
        } catch (RuntimeException e) {
            log.error("Invalid kinect file");
            Alerts.I.raiseError(KINECT_PARSING_ERR);
            throw e;
        }
    }
}
