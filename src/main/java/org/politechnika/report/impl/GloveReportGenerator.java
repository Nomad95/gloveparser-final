package org.politechnika.report.impl;

import org.politechnika.analysis.StatisticsAnalyzer;
import org.politechnika.analysis.impl.GloveStatisticsAnalyzerImpl;
import org.politechnika.commons.Constants;
import org.politechnika.data_parser.csv.definitions.GloveParsingStrategy;
import org.politechnika.data_parser.csv.definitions.beans.GloveDataDto;
import org.politechnika.data_parser.csv.impl.BeanCsvParser;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.model.Finger;
import org.politechnika.model.HandData;
import org.politechnika.report.ReportGenerator;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.politechnika.report.functions.GloveFunctions.sensorToFinger;

public class GloveReportGenerator implements ReportGenerator {

    @Override
    public void generate(AbstractDataFile dataFile) {
        //TODO: load data -> process and aggregate -> save to cache if needed for further processing -> save result to file -> generate and save charts(?)
        BeanCsvParser beanCsvParser = new BeanCsvParser();
        StatisticsAnalyzer<GloveDataDto> statisticsAnalyzer = new GloveStatisticsAnalyzerImpl();
        try {
            List<GloveDataDto> gloveDataDtos = beanCsvParser.parseToBean(dataFile, new GloveParsingStrategy());
            //rozdzielic na lewa i prawa xd
            Map<String, List<GloveDataDto>> dataByHand = gloveDataDtos.stream().collect(groupingBy(GloveDataDto::getHand));

            //lewa
            Map<Finger, List<GloveDataDto>> dataByFingersOfOneHand = dataByHand.get("left").stream().collect(groupingBy(sensorToFinger()));
            //srednie dla calej dlugosci
            HandData handData = new HandData("LEWA RĘKA");
            for (Map.Entry<Finger, List<GloveDataDto>> entry : dataByFingersOfOneHand.entrySet()) {
                handData.setAverageFor(entry.getKey(), statisticsAnalyzer.getAverage(entry.getValue(), GloveDataDto::getRaw));
                handData.setVarianceFor(entry.getKey(), statisticsAnalyzer.hashCode());
            }


            //srednie dla odcinka

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /*
      try {
            List<GloveDataDto> gloveDataDtos = beanCsvParser.parseToBean(dataFile, new GloveParsingStrategy());
            StatisticsAnalyzer<GloveDataDto> gloveStatisticsAnalyzer = new GloveStatisticsAnalyzerImpl();
            List<GloveDataDto> gloveDataDtos1 = gloveStatisticsAnalyzer.averageDataInOneSensor(gloveDataDtos);
            ChartData left = gloveStatisticsAnalyzer.averageOneFingerEvery1secFromHand(gloveDataDtos, "left");
           ChartGeneratorImpl chartGenerator = new ChartGeneratorImpl();

            chartGenerator.drawChart(new Plot.Builder(new Object[]{left.getFinger1(), left.getFinger2(), left.getFinger3(), left.getFinger4(), }, left.getTime())
                    .withFileName("average")
                    .withGrid()
                    .withLegend("{'var1','var2', 'var3', 'var4'}")
                    .withTitle("Average data per finger")
                    .withXAxisName("Time [s]")
                    .withYAxisName("Average value [somethings]")
                    .build("D:\\\\"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
     */

    @Override
    public boolean supports(String fileType) {
        return Constants.GLOVE.equals(fileType);
    }
}
