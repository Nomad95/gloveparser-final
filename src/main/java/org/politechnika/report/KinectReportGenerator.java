package org.politechnika.report;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.Constants;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.report.kinect_functions.*;

@Slf4j
public class KinectReportGenerator implements ReportGenerator {

    @Override //TODO: ekstrapolacja - puls - srednia pomiedzyjedna a druga - rozpoznawanie. przeszukanie ignorowanie zera, czyscimy jesli pulsometr jeszcze nie zacza naliczac pulsu, definicje kolorów dla wartosci, np glowa-szyj-costam jeden kolor,  wskaza maksymalny puls w np pomiedzy 13 - a 15 sekundą, albo np co sie dzeje pomiedzy 1-10 sekundą, mail wszystkie pulsy,
    //TODO: wszystkie JPG wyslac
    //TODO: wykres - nałożone
    public void generate(AbstractDataFile dataFile) {
        GenerateKinectReport generator = GenerateKinectReport.builder()
                .fromFile(dataFile)
                .parseData(new ParseToBeans())
                .doCalculations(new CalculateKinectStatistics()) //invoke charts
                .doCalculationsWithTimeInterval(new CalculateTimeIntervalKinectStatistics()) //invoke charts
                .doPointsCalculations(new CalculatePointDistanceStatistics()
                        .compose(new GenerateKinectPointDistanceReportCsv())) //invoke charts
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
        //TODO: load data -> process and aggregate -> save to cache if needed for further processing -> save result to file (LAZY!)-> generate and save charts
    }

    @Override
    public boolean supports(String fileType) {
        return Constants.KINECT.equals(fileType);
    }
}
