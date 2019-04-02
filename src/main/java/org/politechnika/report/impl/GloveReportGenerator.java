package org.politechnika.report.impl;

import org.politechnika.commons.Constants;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.report.ReportGenerator;
import org.politechnika.report.impl.glove_functions.*;

import static org.politechnika.commons.Constants.LEFT_HAND;
import static org.politechnika.commons.Constants.RIGHT_HAND;

public class GloveReportGenerator implements ReportGenerator {


    @Override
    public void generate(AbstractDataFile dataFile) {
        GenerateGloveReport generator = GenerateGloveReport.builder()
                .fromFile(dataFile)
                .parseData(new ParseToBeans())
                .partitionRawData(new PartitionDataByHand())
                .doOnLeftHand(new CalculateGloveStatistics(LEFT_HAND)
                        .compose(new CreateTimeSegmentedLeftHandRawDataChart())
                        .andThen(new PrintStatistics()))
                .doOnRightHand(new CalculateGloveStatistics(RIGHT_HAND)
                        .compose(new CreateTimeSegmentedRightHandRawDataChart())
                        .andThen(new PrintStatistics()))
                .doOnLeftHandWithTimeInterval(new CalculateTimeIntervalStatistics(LEFT_HAND)
                        .andThen(new CreateTimeSegmentedAverageChart())
                        .andThen(new CreateTimeSegmentedVarianceChart())
                        .andThen(new CreateTimeSegmentedStandardDeviationChart())
                        .andThen(new CreateTimeSegmentedSkewnessChart())
                        .andThen(new CreateTimeSegmentedKurtosisChart())
                        .andThen(new CreateScatterChart())
                        .andThen(new PrintTimeSegmentedStatistics()))
                .doOnRightHandWithTimeInterval(new CalculateTimeIntervalStatistics(RIGHT_HAND)
                        .andThen(new CreateTimeSegmentedAverageChart())
                        .andThen(new CreateTimeSegmentedVarianceChart())
                        .andThen(new CreateTimeSegmentedStandardDeviationChart())
                        .andThen(new CreateTimeSegmentedSkewnessChart())
                        .andThen(new CreateTimeSegmentedKurtosisChart())
                        .andThen(new CreateScatterChart())
                        .andThen(new PrintTimeSegmentedStatistics()))
                .build();

        generator.generate();
    }

    @Override
    public boolean supports(String fileType) {
        return Constants.GLOVE.equals(fileType);
    }
}
