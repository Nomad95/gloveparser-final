package org.politechnika.report;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.cache.EntryType;
import org.politechnika.cache.LoadingDataCache;
import org.politechnika.commons.Constants;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.superimpose.Superimposed;
import org.politechnika.superimpose.standard.StandardSuperimposedChartFactory;

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
        superimposed.adjustSeries();
        LineChart<Number, Number> chart = superimposed.getChart();
        Scene scene  = new Scene(chart,800,600);
        Stage stage = new Stage();
        stage.setResizable(true);
        stage.setTitle("Nałożony wykres");
        stage.setScene(scene);
        stage.show();
        log.debug("Superimposed chart created successfully");
    }

}
