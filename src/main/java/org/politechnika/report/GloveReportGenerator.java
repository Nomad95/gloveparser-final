package org.politechnika.report;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.cache.EntryType;
import org.politechnika.commons.Constants;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.report.glove_functions.*;

import static org.politechnika.commons.Constants.LEFT_HAND;
import static org.politechnika.commons.Constants.RIGHT_HAND;

@Slf4j
public class GloveReportGenerator implements ReportGenerator {

    @Override
    public void generate(AbstractDataFile dataFile) {
        GenerateGloveReport generator = GenerateGloveReport.builder()
                .fromFile(dataFile)
                .parseData(new ParseToBeans())
                .partitionRawData(new PartitionDataByHand())
                .doOnLeftHand(new CalculateGloveStatistics(LEFT_HAND)
                        .compose(new CreateTimeSegmentedLeftHandRawDataChart())
                        .compose(new CreateOneHandRawDataCsv(LEFT_HAND))
                        .compose(new StoreGloveValues(EntryType.LEFT_HAND_VALUES))
                        .compose(new CalculateAndCachePearsonCorrelations(LEFT_HAND))
                        .andThen(new CacheStatistics()))
                .doOnRightHand(new CalculateGloveStatistics(RIGHT_HAND)
                        .compose(new CreateTimeSegmentedRightHandRawDataChart())
                        .compose(new CreateOneHandRawDataCsv(RIGHT_HAND))
                        .compose(new StoreGloveValues(EntryType.RIGHT_HAND_VALUES))
                        .compose(new CalculateAndCachePearsonCorrelations(RIGHT_HAND))
                        .andThen(new CacheStatistics()))
                .doOnLeftHandWithTimeInterval(new CalculateTimeIntervalStatistics(LEFT_HAND)
                        .andThen(new CreateTimeSegmentedAverageChart())
                        .andThen(new CreateTimeSegmentedVarianceChart())
                        .andThen(new CreateTimeSegmentedStandardDeviationChart())
                        .andThen(new CreateTimeSegmentedSkewnessChart())
                        .andThen(new CreateTimeSegmentedKurtosisChart())
                        .andThen(new CreateAverageAndVarianceChart()))
                .doOnRightHandWithTimeInterval(new CalculateTimeIntervalStatistics(RIGHT_HAND)
                        .andThen(new CreateTimeSegmentedAverageChart())
                        .andThen(new CreateTimeSegmentedVarianceChart())
                        .andThen(new CreateTimeSegmentedStandardDeviationChart())
                        .andThen(new CreateTimeSegmentedSkewnessChart())
                        .andThen(new CreateTimeSegmentedKurtosisChart())
                        .andThen(new CreateAverageAndVarianceChart()))
                .build();

        log.debug("Generating glove report");
        generator.generate();
        log.debug("Glove report was generated");
    }

    @Override
    public boolean supports(String fileType) {
        return Constants.GLOVE.equals(fileType);
    }
}
