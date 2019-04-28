package org.politechnika.superimpose;

import org.politechnika.model.glove.GloveValueDto;
import org.politechnika.model.kinect.PointDistanceValueDto;
import org.politechnika.model.pulsometer.PulsometerValueDto;
import org.politechnika.superimpose.standard.SuperimposedChartBundle;

import java.util.List;

public interface Superimposed {

    void loadLeftGloveValues(List<GloveValueDto> values);

    void loadRightGloveValues(List<GloveValueDto> values);

    void loadKinectValues(List<PointDistanceValueDto> values);

    void loadPulsometerValues(List<PulsometerValueDto> values);

    void setProjection(Projection projection);

    void adjustSeries();

    SuperimposedChartBundle getChartBundle();
}
