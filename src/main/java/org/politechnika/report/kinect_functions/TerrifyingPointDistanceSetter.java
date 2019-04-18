package org.politechnika.report.kinect_functions;

import org.politechnika.analysis.StandardStatisticsAnalyzerImpl;
import org.politechnika.model.kinect.PointDistance;
import org.politechnika.model.kinect.PointDistanceStatistics;

import java.util.List;

import static org.politechnika.model.kinect.Sensor.*;

class TerrifyingPointDistanceSetter {

    private final StandardStatisticsAnalyzerImpl statisticAnalyzer;

    TerrifyingPointDistanceSetter() {
        this.statisticAnalyzer = new StandardStatisticsAnalyzerImpl();
    }

    void setAllAverages(PointDistanceStatistics kinectStatistics, List<PointDistance> data) {
        kinectStatistics.setAverageFor(SPINE_BASE, statisticAnalyzer.getAverage(data, PointDistance::getSpineBase));
        kinectStatistics.setAverageFor(SPINE_MID, statisticAnalyzer.getAverage(data, PointDistance::getSpineMid));
        kinectStatistics.setAverageFor(NECK, statisticAnalyzer.getAverage(data, PointDistance::getNeck));
        kinectStatistics.setAverageFor(HEAD, statisticAnalyzer.getAverage(data, PointDistance::getHead));
        kinectStatistics.setAverageFor(SHOULDER_LEFT, statisticAnalyzer.getAverage(data, PointDistance::getShoulderLeft));
        kinectStatistics.setAverageFor(SHOULDER_RIGHT, statisticAnalyzer.getAverage(data, PointDistance::getShoulderRight));
        kinectStatistics.setAverageFor(ELBOW_LEFT, statisticAnalyzer.getAverage(data, PointDistance::getElbowLeft));
        kinectStatistics.setAverageFor(ELBOW_RIGHT, statisticAnalyzer.getAverage(data, PointDistance::getElbowRight));
        kinectStatistics.setAverageFor(WRIST_LEFT, statisticAnalyzer.getAverage(data, PointDistance::getWristLeft));
        kinectStatistics.setAverageFor(WRIST_RIGHT, statisticAnalyzer.getAverage(data, PointDistance::getWristRight));
        kinectStatistics.setAverageFor(HAND_LEFT, statisticAnalyzer.getAverage(data, PointDistance::getHandLeft));
        kinectStatistics.setAverageFor(HAND_RIGHT, statisticAnalyzer.getAverage(data, PointDistance::getHandRight));
        kinectStatistics.setAverageFor(HIP_LEFT, statisticAnalyzer.getAverage(data, PointDistance::getHipLeft));
        kinectStatistics.setAverageFor(HIP_RIGHT, statisticAnalyzer.getAverage(data, PointDistance::getHipRight));
        kinectStatistics.setAverageFor(KNEE_LEFT, statisticAnalyzer.getAverage(data, PointDistance::getKneeLeft));
        kinectStatistics.setAverageFor(KNEE_RIGHT, statisticAnalyzer.getAverage(data, PointDistance::getKneeRight));
        kinectStatistics.setAverageFor(ANKLE_LEFT, statisticAnalyzer.getAverage(data, PointDistance::getAnkleLeft));
        kinectStatistics.setAverageFor(ANKLE_RIGHT, statisticAnalyzer.getAverage(data, PointDistance::getAnkleRight));
        kinectStatistics.setAverageFor(FOOT_LEFT, statisticAnalyzer.getAverage(data, PointDistance::getFootLeft));
        kinectStatistics.setAverageFor(FOOT_RIGHT, statisticAnalyzer.getAverage(data, PointDistance::getFootRight));
        kinectStatistics.setAverageFor(SPINE_SHOULDER, statisticAnalyzer.getAverage(data, PointDistance::getSpineShoulder));
        kinectStatistics.setAverageFor(HAND_TIP_LEFT, statisticAnalyzer.getAverage(data, PointDistance::getHandTipLeft));
        kinectStatistics.setAverageFor(HAND_TIP_RIGHT, statisticAnalyzer.getAverage(data, PointDistance::getHandTipRight));
        kinectStatistics.setAverageFor(THUMB_LEFT, statisticAnalyzer.getAverage(data, PointDistance::getThumbLeft));
        kinectStatistics.setAverageFor(THUMB_RIGHT, statisticAnalyzer.getAverage(data, PointDistance::getThumbRight));
    }

    void setAllVariances(PointDistanceStatistics kinectStatistics, List<PointDistance> data) {
        kinectStatistics.setVarianceFor(SPINE_BASE, statisticAnalyzer.getVariance(data, PointDistance::getSpineBase));
        kinectStatistics.setVarianceFor(SPINE_MID, statisticAnalyzer.getVariance(data, PointDistance::getSpineMid));
        kinectStatistics.setVarianceFor(NECK, statisticAnalyzer.getVariance(data, PointDistance::getNeck));
        kinectStatistics.setVarianceFor(HEAD, statisticAnalyzer.getVariance(data, PointDistance::getHead));
        kinectStatistics.setVarianceFor(SHOULDER_LEFT, statisticAnalyzer.getVariance(data, PointDistance::getShoulderLeft));
        kinectStatistics.setVarianceFor(SHOULDER_RIGHT, statisticAnalyzer.getVariance(data, PointDistance::getShoulderRight));
        kinectStatistics.setVarianceFor(ELBOW_LEFT, statisticAnalyzer.getVariance(data, PointDistance::getElbowLeft));
        kinectStatistics.setVarianceFor(ELBOW_RIGHT, statisticAnalyzer.getVariance(data, PointDistance::getElbowRight));
        kinectStatistics.setVarianceFor(WRIST_LEFT, statisticAnalyzer.getVariance(data, PointDistance::getWristLeft));
        kinectStatistics.setVarianceFor(WRIST_RIGHT, statisticAnalyzer.getVariance(data, PointDistance::getWristRight));
        kinectStatistics.setVarianceFor(HAND_LEFT, statisticAnalyzer.getVariance(data, PointDistance::getHandLeft));
        kinectStatistics.setVarianceFor(HAND_RIGHT, statisticAnalyzer.getVariance(data, PointDistance::getHandRight));
        kinectStatistics.setVarianceFor(HIP_LEFT, statisticAnalyzer.getVariance(data, PointDistance::getHipLeft));
        kinectStatistics.setVarianceFor(HIP_RIGHT, statisticAnalyzer.getVariance(data, PointDistance::getHipRight));
        kinectStatistics.setVarianceFor(KNEE_LEFT, statisticAnalyzer.getVariance(data, PointDistance::getKneeLeft));
        kinectStatistics.setVarianceFor(KNEE_RIGHT, statisticAnalyzer.getVariance(data, PointDistance::getKneeRight));
        kinectStatistics.setVarianceFor(ANKLE_LEFT, statisticAnalyzer.getVariance(data, PointDistance::getAnkleLeft));
        kinectStatistics.setVarianceFor(ANKLE_RIGHT, statisticAnalyzer.getVariance(data, PointDistance::getAnkleRight));
        kinectStatistics.setVarianceFor(FOOT_LEFT, statisticAnalyzer.getVariance(data, PointDistance::getFootLeft));
        kinectStatistics.setVarianceFor(FOOT_RIGHT, statisticAnalyzer.getVariance(data, PointDistance::getFootRight));
        kinectStatistics.setVarianceFor(SPINE_SHOULDER, statisticAnalyzer.getVariance(data, PointDistance::getSpineShoulder));
        kinectStatistics.setVarianceFor(HAND_TIP_LEFT, statisticAnalyzer.getVariance(data, PointDistance::getHandTipLeft));
        kinectStatistics.setVarianceFor(HAND_TIP_RIGHT, statisticAnalyzer.getVariance(data, PointDistance::getHandTipRight));
        kinectStatistics.setVarianceFor(THUMB_LEFT, statisticAnalyzer.getVariance(data, PointDistance::getThumbLeft));
        kinectStatistics.setVarianceFor(THUMB_RIGHT, statisticAnalyzer.getVariance(data, PointDistance::getThumbRight));
    }

    void setAllStandardDeviations(PointDistanceStatistics kinectStatistics, List<PointDistance> data) {
        kinectStatistics.setStandardDeviationFor(SPINE_BASE, statisticAnalyzer.getStandardDeviation(data, PointDistance::getSpineBase));
        kinectStatistics.setStandardDeviationFor(SPINE_MID, statisticAnalyzer.getStandardDeviation(data, PointDistance::getSpineMid));
        kinectStatistics.setStandardDeviationFor(NECK, statisticAnalyzer.getStandardDeviation(data, PointDistance::getNeck));
        kinectStatistics.setStandardDeviationFor(HEAD, statisticAnalyzer.getStandardDeviation(data, PointDistance::getHead));
        kinectStatistics.setStandardDeviationFor(SHOULDER_LEFT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getShoulderLeft));
        kinectStatistics.setStandardDeviationFor(SHOULDER_RIGHT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getShoulderRight));
        kinectStatistics.setStandardDeviationFor(ELBOW_LEFT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getElbowLeft));
        kinectStatistics.setStandardDeviationFor(ELBOW_RIGHT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getElbowRight));
        kinectStatistics.setStandardDeviationFor(WRIST_LEFT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getWristLeft));
        kinectStatistics.setStandardDeviationFor(WRIST_RIGHT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getWristRight));
        kinectStatistics.setStandardDeviationFor(HAND_LEFT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getHandLeft));
        kinectStatistics.setStandardDeviationFor(HAND_RIGHT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getHandRight));
        kinectStatistics.setStandardDeviationFor(HIP_LEFT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getHipLeft));
        kinectStatistics.setStandardDeviationFor(HIP_RIGHT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getHipRight));
        kinectStatistics.setStandardDeviationFor(KNEE_LEFT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getKneeLeft));
        kinectStatistics.setStandardDeviationFor(KNEE_RIGHT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getKneeRight));
        kinectStatistics.setStandardDeviationFor(ANKLE_LEFT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getAnkleLeft));
        kinectStatistics.setStandardDeviationFor(ANKLE_RIGHT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getAnkleRight));
        kinectStatistics.setStandardDeviationFor(FOOT_LEFT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getFootLeft));
        kinectStatistics.setStandardDeviationFor(FOOT_RIGHT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getFootRight));
        kinectStatistics.setStandardDeviationFor(SPINE_SHOULDER, statisticAnalyzer.getStandardDeviation(data, PointDistance::getSpineShoulder));
        kinectStatistics.setStandardDeviationFor(HAND_TIP_LEFT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getHandTipLeft));
        kinectStatistics.setStandardDeviationFor(HAND_TIP_RIGHT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getHandTipRight));
        kinectStatistics.setStandardDeviationFor(THUMB_LEFT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getThumbLeft));
        kinectStatistics.setStandardDeviationFor(THUMB_RIGHT, statisticAnalyzer.getStandardDeviation(data, PointDistance::getThumbRight));
    }

    void setSkewnessCoefficients(PointDistanceStatistics kinectStatistics, List<PointDistance> data) {
        kinectStatistics.setSkewnessCoefficientFor(SPINE_BASE, statisticAnalyzer.getSkewness(data, PointDistance::getSpineBase));
        kinectStatistics.setSkewnessCoefficientFor(SPINE_MID, statisticAnalyzer.getSkewness(data, PointDistance::getSpineMid));
        kinectStatistics.setSkewnessCoefficientFor(NECK, statisticAnalyzer.getSkewness(data, PointDistance::getNeck));
        kinectStatistics.setSkewnessCoefficientFor(HEAD, statisticAnalyzer.getSkewness(data, PointDistance::getHead));
        kinectStatistics.setSkewnessCoefficientFor(SHOULDER_LEFT, statisticAnalyzer.getSkewness(data, PointDistance::getShoulderLeft));
        kinectStatistics.setSkewnessCoefficientFor(SHOULDER_RIGHT, statisticAnalyzer.getSkewness(data, PointDistance::getShoulderRight));
        kinectStatistics.setSkewnessCoefficientFor(ELBOW_LEFT, statisticAnalyzer.getSkewness(data, PointDistance::getElbowLeft));
        kinectStatistics.setSkewnessCoefficientFor(ELBOW_RIGHT, statisticAnalyzer.getSkewness(data, PointDistance::getElbowRight));
        kinectStatistics.setSkewnessCoefficientFor(WRIST_LEFT, statisticAnalyzer.getSkewness(data, PointDistance::getWristLeft));
        kinectStatistics.setSkewnessCoefficientFor(WRIST_RIGHT, statisticAnalyzer.getSkewness(data, PointDistance::getWristRight));
        kinectStatistics.setSkewnessCoefficientFor(HAND_LEFT, statisticAnalyzer.getSkewness(data, PointDistance::getHandLeft));
        kinectStatistics.setSkewnessCoefficientFor(HAND_RIGHT, statisticAnalyzer.getSkewness(data, PointDistance::getHandRight));
        kinectStatistics.setSkewnessCoefficientFor(HIP_LEFT, statisticAnalyzer.getSkewness(data, PointDistance::getHipLeft));
        kinectStatistics.setSkewnessCoefficientFor(HIP_RIGHT, statisticAnalyzer.getSkewness(data, PointDistance::getHipRight));
        kinectStatistics.setSkewnessCoefficientFor(KNEE_LEFT, statisticAnalyzer.getSkewness(data, PointDistance::getKneeLeft));
        kinectStatistics.setSkewnessCoefficientFor(KNEE_RIGHT, statisticAnalyzer.getSkewness(data, PointDistance::getKneeRight));
        kinectStatistics.setSkewnessCoefficientFor(ANKLE_LEFT, statisticAnalyzer.getSkewness(data, PointDistance::getAnkleLeft));
        kinectStatistics.setSkewnessCoefficientFor(ANKLE_RIGHT, statisticAnalyzer.getSkewness(data, PointDistance::getAnkleRight));
        kinectStatistics.setSkewnessCoefficientFor(FOOT_LEFT, statisticAnalyzer.getSkewness(data, PointDistance::getFootLeft));
        kinectStatistics.setSkewnessCoefficientFor(FOOT_RIGHT, statisticAnalyzer.getSkewness(data, PointDistance::getFootRight));
        kinectStatistics.setSkewnessCoefficientFor(SPINE_SHOULDER, statisticAnalyzer.getSkewness(data, PointDistance::getSpineShoulder));
        kinectStatistics.setSkewnessCoefficientFor(HAND_TIP_LEFT, statisticAnalyzer.getSkewness(data, PointDistance::getHandTipLeft));
        kinectStatistics.setSkewnessCoefficientFor(HAND_TIP_RIGHT, statisticAnalyzer.getSkewness(data, PointDistance::getHandTipRight));
        kinectStatistics.setSkewnessCoefficientFor(THUMB_LEFT, statisticAnalyzer.getSkewness(data, PointDistance::getThumbLeft));
        kinectStatistics.setSkewnessCoefficientFor(THUMB_RIGHT, statisticAnalyzer.getSkewness(data, PointDistance::getThumbRight));
    }

    void setAllKurtosis(PointDistanceStatistics kinectStatistics, List<PointDistance> data) {
        kinectStatistics.setKurtosisFor(SPINE_BASE, statisticAnalyzer.getKurtosis(data, PointDistance::getSpineBase));
        kinectStatistics.setKurtosisFor(SPINE_MID, statisticAnalyzer.getKurtosis(data, PointDistance::getSpineMid));
        kinectStatistics.setKurtosisFor(NECK, statisticAnalyzer.getKurtosis(data, PointDistance::getNeck));
        kinectStatistics.setKurtosisFor(HEAD, statisticAnalyzer.getKurtosis(data, PointDistance::getHead));
        kinectStatistics.setKurtosisFor(SHOULDER_LEFT, statisticAnalyzer.getKurtosis(data, PointDistance::getShoulderLeft));
        kinectStatistics.setKurtosisFor(SHOULDER_RIGHT, statisticAnalyzer.getKurtosis(data, PointDistance::getShoulderRight));
        kinectStatistics.setKurtosisFor(ELBOW_LEFT, statisticAnalyzer.getKurtosis(data, PointDistance::getElbowLeft));
        kinectStatistics.setKurtosisFor(ELBOW_RIGHT, statisticAnalyzer.getKurtosis(data, PointDistance::getElbowRight));
        kinectStatistics.setKurtosisFor(WRIST_LEFT, statisticAnalyzer.getKurtosis(data, PointDistance::getWristLeft));
        kinectStatistics.setKurtosisFor(WRIST_RIGHT, statisticAnalyzer.getKurtosis(data, PointDistance::getWristRight));
        kinectStatistics.setKurtosisFor(HAND_LEFT, statisticAnalyzer.getKurtosis(data, PointDistance::getHandLeft));
        kinectStatistics.setKurtosisFor(HAND_RIGHT, statisticAnalyzer.getKurtosis(data, PointDistance::getHandRight));
        kinectStatistics.setKurtosisFor(HIP_LEFT, statisticAnalyzer.getKurtosis(data, PointDistance::getHipLeft));
        kinectStatistics.setKurtosisFor(HIP_RIGHT, statisticAnalyzer.getKurtosis(data, PointDistance::getHipRight));
        kinectStatistics.setKurtosisFor(KNEE_LEFT, statisticAnalyzer.getKurtosis(data, PointDistance::getKneeLeft));
        kinectStatistics.setKurtosisFor(KNEE_RIGHT, statisticAnalyzer.getKurtosis(data, PointDistance::getKneeRight));
        kinectStatistics.setKurtosisFor(ANKLE_LEFT, statisticAnalyzer.getKurtosis(data, PointDistance::getAnkleLeft));
        kinectStatistics.setKurtosisFor(ANKLE_RIGHT, statisticAnalyzer.getKurtosis(data, PointDistance::getAnkleRight));
        kinectStatistics.setKurtosisFor(FOOT_LEFT, statisticAnalyzer.getKurtosis(data, PointDistance::getFootLeft));
        kinectStatistics.setKurtosisFor(FOOT_RIGHT, statisticAnalyzer.getKurtosis(data, PointDistance::getFootRight));
        kinectStatistics.setKurtosisFor(SPINE_SHOULDER, statisticAnalyzer.getKurtosis(data, PointDistance::getSpineShoulder));
        kinectStatistics.setKurtosisFor(HAND_TIP_LEFT, statisticAnalyzer.getKurtosis(data, PointDistance::getHandTipLeft));
        kinectStatistics.setKurtosisFor(HAND_TIP_RIGHT, statisticAnalyzer.getKurtosis(data, PointDistance::getHandTipRight));
        kinectStatistics.setKurtosisFor(THUMB_LEFT, statisticAnalyzer.getKurtosis(data, PointDistance::getThumbLeft));
        kinectStatistics.setKurtosisFor(THUMB_RIGHT, statisticAnalyzer.getKurtosis(data, PointDistance::getThumbRight));
    }
}
