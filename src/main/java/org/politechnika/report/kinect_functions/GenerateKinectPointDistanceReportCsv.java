package org.politechnika.report.kinect_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.data_parser.CsvParsingException;
import org.politechnika.data_parser.model.KinectDataDto;
import org.politechnika.file.FileWriter;
import org.politechnika.model.kinect.PointDistance;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class GenerateKinectPointDistanceReportCsv implements Function<List<KinectDataDto>, List<KinectDataDto>> {

    private PointVectorLengthCalculator vectorLengthCalculator;
    private FileWriter fileWriter;

    public GenerateKinectPointDistanceReportCsv() {
        this.vectorLengthCalculator = new PointVectorLengthCalculator();
        this.fileWriter = new FileWriter();
    }

    @Override
    public List<KinectDataDto> apply(List<KinectDataDto> kinectDataDtos) {
        List<PointDistance> points = new LinkedList<>();
        for (int i = 1 ; i < kinectDataDtos.size() - 1 ; i++) {
            points.add(calculatePoint(kinectDataDtos.get(i), kinectDataDtos.get(i-1), i));
        }

        tryWriteToCsv(points);

        return kinectDataDtos;
    }

    private PointDistance calculatePoint(KinectDataDto data, KinectDataDto data1, int i) {
        PointDistance pointDistance = new PointDistance("Point " + i);
        vectorLengthCalculator.calculateVectorLength(pointDistance, data1, data);
        return pointDistance;
    }

    private void tryWriteToCsv(List<PointDistance> points) {
        try {
            fileWriter.writeToCsvFile(points, "/kinect_points_distance.csv");
        } catch (CsvParsingException e) {
            log.error("Could not create a kinect point distance csv: {}", e.getMessage());
        }
    }

}
