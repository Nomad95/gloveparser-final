package org.politechnika.report.impl;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.Constants;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.report.ReportGenerator;
import org.politechnika.report.impl.kinect_functions.*;

@Slf4j
public class KinectReportGenerator implements ReportGenerator {

    @Override
    public void generate(AbstractDataFile dataFile) {
        GenerateKinectReport generator = GenerateKinectReport.builder()
                .fromFile(dataFile)
                .parseData(new ParseToBeans())
                .doCalculations(new CalculateKinectStatistics()) //invoke charts
                .doCalculationsWithTimeInterval(new CalculateTimeIntervalKinectStatistics()) //invoke charts
                .doPointsCalculations(new CalculatePointDistanceStatistics()) //invoke charts
                .doPointsCalculationsWithTimeInterval(new CalculateTimeIntervalPointDistanceStatistics()
                        .andThen(new CreateTimeSegmentAverageCoreChart())
                        .andThen(new CreateTimeSegmentAverageLeftArmChart())
                        .andThen(new CreateTimeSegmentAverageRightArmChart())
                        .andThen(new CreateTimeSegmentAverageLeftLegChart())
                        .andThen(new CreateTimeSegmentAverageRightLegChart())
                ) //invoke  charts
                .build();

        log.debug("Generating Kinect report");
        generator.generate();
        log.debug("Kinect report was generated");
        //TODO: load data -> process and aggregate -> save to cache if needed for further processing -> save result to file (LAZY!)-> generate and save charts
    }

    @Override
    public boolean supports(String fileType) {
        return Constants.KINECT.equals(fileType);
    }
}
