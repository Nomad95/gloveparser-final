package org.politechnika.report.impl;

import org.politechnika.commons.Constants;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.report.ReportGenerator;
import org.politechnika.report.impl.glove_functions.*;

public class GloveReportGenerator implements ReportGenerator {

    private static final int TEMP_INTERVAL = 1000;

    @Override
    @SuppressWarnings("Duplicates")
    public void generate(AbstractDataFile dataFile) {
        GenerateGloveReport generator = GenerateGloveReport.builder()
                .fromFile(dataFile)
                .parseData(new ParseToBeans())
                .partitionRawData(new PartitionDataByHand())
                .doOnOneHand(new CalculateStatistics()
                        .andThen(new CreateWholeAverageChart())
                        .andThen(new CreateWholeVarianceChart())
                        .andThen(new CreateWholeStandardDeviationChart())
                        .andThen(new CreateWholeSkewnessChart())
                        .andThen(new CreateWholeKurtosisChart())
                        .andThen(new PrintStatistics()))
                .doOnOneHandWithTimeInterval(new CalculateTimeIntervalStatistics()
                        .andThen(new CreateTimeSegmentedAverageChart())
                        .andThen(new CreateTimeSegmentedVarianceChart())
                        .andThen(new CreateTimeSegmentedStandardDeviationChart())
                        .andThen(new CreateTimeSegmentedSkewnessChart())
                        .andThen(new CreateTimeSegmentedKurtosisChart())
                        .andThen(new PrintTimeSegmentedStatistics()))
                .build();

        generator.generate();
    }

    /*
      try {
            List<GloveDataDto> gloveDataDtos = beanCsvParser.parseToBean(dataFile, new GloveParsingStrategy());
            SimpleStatisticsAnalyzer<GloveDataDto> gloveStatisticsAnalyzer = new GloveStatisticsAnalyzerImpl();
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
