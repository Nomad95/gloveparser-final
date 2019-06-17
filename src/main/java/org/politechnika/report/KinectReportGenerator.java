package org.politechnika.report;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.Constants;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.report.kinect_functions.*;

@Slf4j
public class KinectReportGenerator implements ReportGenerator {

    @Override
    public void generate(AbstractDataFile dataFile) {
        GenerateKinectReport generator = GenerateKinectReport.builder()
                .fromFile(dataFile)
                .parseData(new ParseToBeans())
                .doCalculations(new CalculateKinectStatistics())
                .doCalculationsWithTimeInterval(new CalculateTimeIntervalKinectStatistics()) //invoke charts
                .doPointsCalculations(new CalculatePointDistances()
                        .andThen(new GenerateKinectPointDistanceReportCsv())
                        .andThen(new StorePointDistanceValues())
                        .andThen(new CalculateAndCachePearsonCorrelations())
                        .andThen(new CalculatePointDistanceStatistics())
                        .andThen(new CacheStatistics())) //invoke charts
                .doPointsCalculationsWithTimeInterval(new CalculateTimeIntervalPointDistanceStatistics()
                        .andThen(new CreateTimeSegmentAverageCoreChart()) //średnia
                        .andThen(new CreateTimeSegmentAverageLeftArmChart())
                        .andThen(new CreateTimeSegmentAverageRightArmChart())
                        .andThen(new CreateTimeSegmentAverageLeftLegChart())
                        .andThen(new CreateTimeSegmentAverageRightLegChart())
                        .andThen(new CreateTimeSegmentVarianceCoreChart()) //wariancja
                        .andThen(new CreateTimeSegmentVarianceLeftArmChart())
                        .andThen(new CreateTimeSegmentVarianceLeftLegChart())
                        .andThen(new CreateTimeSegmentVarianceRightArmChart())
                        .andThen(new CreateTimeSegmentVarianceRightLegChart())
                        .andThen(new CreateTimeSegmentStandardDeviationCoreChart()) //odchylenie standardowe
                        .andThen(new CreateTimeSegmentStandardDeviationLeftArmChart())
                        .andThen(new CreateTimeSegmentStandardDeviationLeftLegChart())
                        .andThen(new CreateTimeSegmentStandardDeviationRightArmChart())
                        .andThen(new CreateTimeSegmentStandardDeviationRightLegChart())
                        .andThen(new CreateTimeSegmentSkewnessCoreChart()) //skośność
                        .andThen(new CreateTimeSegmentSkewnessLeftArmChart())
                        .andThen(new CreateTimeSegmentSkewnessLeftLegChart())
                        .andThen(new CreateTimeSegmentSkewnessRightArmChart())
                        .andThen(new CreateTimeSegmentSkewnessRightLegChart())
                ) //invoke  charts
                .build();

        log.debug("Generating Kinect report");
        generator.generate();
        log.debug("Kinect report was generated");
    }

    @Override
    public boolean supports(String fileType) {
        return Constants.KINECT.equals(fileType);
    }
}
