package org.politechnika.report.impl;

import org.politechnika.commons.Constants;
import org.politechnika.data_parser.csv.definitions.GloveParsingStrategy;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;
import org.politechnika.data_parser.csv.impl.BeanCsvParser;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.report.ReportGenerator;

import java.io.FileNotFoundException;
import java.util.List;

public class GloveReportGenerator implements ReportGenerator {

    @Override
    public void generate(AbstractDataFile dataFile) {
        //TODO: load data -> process and aggregate -> save to cache if needed for further processing -> save result to file -> generate and save charts(?)
        BeanCsvParser beanCsvParser = new BeanCsvParser();
        try {
            List<GloveDataDto> gloveDataDtos = beanCsvParser.parseToBean(dataFile, new GloveParsingStrategy());
            //rozdzielic na lewa i prawa xd
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean supports(String fileType) {
        return Constants.GLOVE.equals(fileType);
    }
}
