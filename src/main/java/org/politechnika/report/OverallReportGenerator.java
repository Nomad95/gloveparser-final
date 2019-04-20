package org.politechnika.report;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.politechnika.cache.LoadingStringCache;
import org.politechnika.commons.Constants;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.frontend.MainController;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.politechnika.cache.LoadingStringCache.EntryType.*;
import static org.politechnika.commons.Separators.NEWLINE;

@Slf4j
public class OverallReportGenerator implements CollectiveReportGenerator {

    @Override
    public void generate(List<AbstractDataFile> files) {
        log.debug("Generating overall statistics report");
        StringBuilder sb = new StringBuilder();
        for (AbstractDataFile file : files) {
            if (Constants.GLOVE.equals(file.getFileType())) {
                sb.append("-------------- Statystyki dla rękawic 5DT (kciuk, wskazujący, środkowy, serdeczny, mały)--------------")
                        .append(NEWLINE).append(NEWLINE)
                        .append(LoadingStringCache.get(LEFT_HAND_STATS))
                        .append(NEWLINE)
                        .append(LoadingStringCache.get(RIGHT_HAND_STATS))
                        .append(NEWLINE);
            }
            else if (Constants.KINECT.equals(file.getFileType())) {
                sb.append("-------------- Statystyki dla Kinect - normy --------------")
                        .append(NEWLINE).append(NEWLINE)
                        .append(LoadingStringCache.get(KINECT_STATS))
                        .append(NEWLINE);
            }
            else if (Constants.PULSOMETER.equals(file.getFileType())) {
                sb.append("-------------- Statystyki dla Pulsometru --------------")
                        .append(NEWLINE).append(NEWLINE)
                        .append(LoadingStringCache.get(PULS_STATS))
                        .append(NEWLINE);
            }
        }
        String report = sb.toString();
        if (report.isEmpty()) return;

        File file = new File(MainController.getDestinationSubFolder() + "/statistics.txt");
        try {
            FileUtils.writeStringToFile(file, report);
        } catch (IOException e) {
            //TODO: hehe
            e.printStackTrace();
        }
        log.debug("Overall statistics report was generated");
    }

}
