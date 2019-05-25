package org.politechnika.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.cache.EntryType;
import org.politechnika.cache.LoadingDataCache;
import org.politechnika.cache.ProjectionCache;
import org.politechnika.commons.Constants;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.report.superimposed_functions.DrawSuperimposedChart;
import org.politechnika.superimpose.Superimposed;
import org.politechnika.superimpose.standard.StandardSuperimposedChartFactory;
import org.politechnika.superimpose.standard.SuperimposedChartBundle;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class SuperimposedChartGenerator implements CollectiveReportGenerator {

    private final StandardSuperimposedChartFactory factory;

    @Override
    public void generate(List<AbstractDataFile> files) {
        if (files.isEmpty())
            return;
        log.debug("Creating superimposed chart");
        Superimposed superimposed = factory.getSuperimposedChartGenerator();
        for (AbstractDataFile file : files) {
            switch (file.getFileType()) {
                case Constants.PULSOMETER:
                    superimposed.loadPulsometerValues(LoadingDataCache.get(EntryType.PULSOMETER_VALUES));
                    break;
                case Constants.GLOVE:
                    superimposed.loadRightGloveValues(LoadingDataCache.get(EntryType.RIGHT_HAND_VALUES));
                    superimposed.loadLeftGloveValues(LoadingDataCache.get(EntryType.LEFT_HAND_VALUES));
                    break;
                case Constants.KINECT:
                    superimposed.loadKinectValues(LoadingDataCache.get(EntryType.POINT_DISTANCE_VALUES));
                    break;
            }
        }
        superimposed.setProjection(ProjectionCache.I.getProjection());
        superimposed.adjustSeries();
        SuperimposedChartBundle chartBundle = superimposed.getChartBundle();
        new DrawSuperimposedChart().drawSuperimposedChart(chartBundle);
        log.debug("Superimposed chart created successfully");
    }

}
