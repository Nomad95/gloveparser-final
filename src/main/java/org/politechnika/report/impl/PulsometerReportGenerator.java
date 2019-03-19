package org.politechnika.report.impl;

import org.politechnika.analysis.impl.PulsometerStatisticAnalyzerImpl;
import org.politechnika.commons.Constants;
import org.politechnika.data_parser.csv.definitions.GloveParsingStrategy;
import org.politechnika.data_parser.csv.definitions.PulsometerParsingStrategy;
import org.politechnika.data_parser.csv.definitions.beans.PulsometerDataDto;
import org.politechnika.data_parser.csv.impl.BeanCsvParser;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.report.ReportGenerator;

import java.io.FileNotFoundException;
import java.util.List;

public class PulsometerReportGenerator implements ReportGenerator {
    @Override
    public void generate(AbstractDataFile dataFile) {

        BeanCsvParser beanCsvParser = new BeanCsvParser();
        List<PulsometerDataDto> pulsometerDataDtos;
        try {
            pulsometerDataDtos = beanCsvParser.parseToBean(dataFile, new PulsometerParsingStrategy());
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }

        PulsometerStatisticAnalyzerImpl analyzer = new PulsometerStatisticAnalyzerImpl();
        double average = analyzer.getAverage(pulsometerDataDtos, PulsometerDataDto::getValue);
        double kurtosis = analyzer.getKurtosis(pulsometerDataDtos, PulsometerDataDto::getValue);
        double skewness = analyzer.getSkewness(pulsometerDataDtos, PulsometerDataDto::getValue);
        double deviation = analyzer.getStandardDeviation(pulsometerDataDtos, PulsometerDataDto::getValue);
        double variance = analyzer.getVariance(pulsometerDataDtos, PulsometerDataDto::getValue);


        //TODO: load data
        // -> process and aggregate
        // -> save to cache if needed for further processing
        // -> save result to file (LAZY!)
        // -> generate and save charts
    }

    @Override
    public boolean supports(String fileType) {
        return Constants.PULSOMETER.equals(fileType);
    }
}
