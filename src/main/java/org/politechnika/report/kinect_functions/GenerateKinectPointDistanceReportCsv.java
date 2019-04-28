package org.politechnika.report.kinect_functions;

import lombok.extern.slf4j.Slf4j;
import org.politechnika.data_parser.CsvParsingException;
import org.politechnika.file.FileWriter;
import org.politechnika.model.kinect.PointDistance;

import java.util.List;
import java.util.function.UnaryOperator;

@Slf4j
public class GenerateKinectPointDistanceReportCsv implements UnaryOperator<List<PointDistance>> {

    private FileWriter fileWriter;

    public GenerateKinectPointDistanceReportCsv() {
        this.fileWriter = new FileWriter();
    }

    @Override
    public List<PointDistance> apply(List<PointDistance> points) {
        tryWriteToCsv(points);
        return points;
    }

    private void tryWriteToCsv(List<PointDistance> points) {
        try {
            fileWriter.writeToCsvFile(points, "/kinect_points_distance.csv");
        } catch (CsvParsingException e) {
            log.error("Could not create a kinect point distance csv: {}", e.getMessage());
        }
    }

}
