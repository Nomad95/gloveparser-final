package org.politechnika.report.kinect_functions;

import org.politechnika.cache.LoadingDataCache;
import org.politechnika.model.kinect.PointDistance;
import org.politechnika.model.kinect.PointDistanceValueDto;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static org.politechnika.cache.EntryType.POINT_DISTANCE_VALUES;

public class StorePointDistanceValues implements UnaryOperator<List<PointDistance>> {

    @Override
    public List<PointDistance> apply(List<PointDistance> kinectDataDtos) {
        List<PointDistanceValueDto> values = kinectDataDtos.stream()
                .map(p ->
                        new PointDistanceValueDto(
                                p.getSpineBase(),
                                p.getSpineMid(),
                                p.getNeck(),
                                p.getHead(),
                                p.getShoulderLeft(),
                                p.getElbowLeft(),
                                p.getWristLeft(),
                                p.getHandLeft(),
                                p.getShoulderRight(),
                                p.getElbowRight(),
                                p.getWristRight(),
                                p.getHandRight(),
                                p.getHipLeft(),
                                p.getKneeLeft(),
                                p.getAnkleLeft(),
                                p.getFootLeft(),
                                p.getHipRight(),
                                p.getKneeRight(),
                                p.getAnkleRight(),
                                p.getFootRight(),
                                p.getSpineShoulder(),
                                p.getHandTipLeft(),
                                p.getThumbLeft(),
                                p.getHandTipRight(),
                                p.getThumbRight(),
                                p.getTime()))
                .collect(Collectors.toCollection(LinkedList::new));

        LoadingDataCache.put(POINT_DISTANCE_VALUES, values);
        return kinectDataDtos;
    }
}
