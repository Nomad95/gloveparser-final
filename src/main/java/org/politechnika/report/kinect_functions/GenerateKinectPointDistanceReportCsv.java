package org.politechnika.report.kinect_functions;

import org.politechnika.data_parser.BeanToCsvParser;
import org.politechnika.data_parser.CsvParsingException;
import org.politechnika.data_parser.model.KinectDataDto;
import org.politechnika.frontend.MainController;
import org.politechnika.model.kinect.PointDistance;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class GenerateKinectPointDistanceReportCsv implements Function<List<KinectDataDto>, List<KinectDataDto>> {

    private PointVectorLengthCalculator vectorLengthCalculator;

    public GenerateKinectPointDistanceReportCsv() {
        this.vectorLengthCalculator = new PointVectorLengthCalculator();
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
            new BeanToCsvParser().parseToCsv(points, MainController.getDestinationSubFolder() + "/kinect_points_distance.csv");
        } catch (CsvParsingException e) {
            //todo: add to errors
            e.printStackTrace();
        }
    }

}
