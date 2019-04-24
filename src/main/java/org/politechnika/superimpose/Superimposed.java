package org.politechnika.superimpose;

import javafx.scene.chart.LineChart;
import org.politechnika.model.glove.GloveValueDto;
import org.politechnika.model.kinect.PointDistanceValueDto;
import org.politechnika.model.pulsometer.PulsometerValueDto;

import java.util.List;

public interface Superimposed {

    void loadLeftGloveValues(List<GloveValueDto> values);

    void loadRightGloveValues(List<GloveValueDto> values);

    void loadKinectValues(List<PointDistanceValueDto> values);

    void loadPulsometerValues(List<PulsometerValueDto> values);

    void setProjection(Projection projection);

    void adjustSeries();

    LineChart<Number, Number> getChart();
}
