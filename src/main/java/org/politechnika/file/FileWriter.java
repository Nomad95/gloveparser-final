package org.politechnika.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.politechnika.data_parser.BeanToCsvParser;
import org.politechnika.data_parser.CsvParsingException;
import org.politechnika.data_parser.model.DataDto;
import org.politechnika.frontend.MainController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class FileWriter {

    public void saveTextFile(String text, String path) throws ParserWriteFileException {
        File file = new File(MainController.getDestinationSubFolder() + path);
        try {
            FileUtils.writeStringToFile(file, text);
        } catch (IOException e) {
            log.error("There was an error while writing a file", e);
            throw new ParserWriteFileException("Could not write to file");
        }
    }

    public void writeToCsvFile(List<? extends DataDto> data, String path) throws CsvParsingException {
        new BeanToCsvParser().parseToCsv(data, MainController.getDestinationSubFolder() + path);
    }
}
