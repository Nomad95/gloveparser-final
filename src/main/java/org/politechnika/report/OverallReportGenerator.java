package org.politechnika.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.cache.LoadingStringCache;
import org.politechnika.commons.Constants;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.file.FileWriter;
import org.politechnika.file.ParserWriteFileException;

import java.util.List;

import static org.politechnika.cache.EntryType.*;
import static org.politechnika.commons.Separators.NEWLINE;

@Slf4j
@RequiredArgsConstructor
public class OverallReportGenerator implements CollectiveReportGenerator {

    private final FileWriter fileWriter;

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

        try {
            fileWriter.saveTextFile(report, "/statistics.txt");
        } catch (ParserWriteFileException e) {
            log.error("Could not save statistic report {}!", e.getMessage());
        }
        log.debug("Overall statistics report was generated");
    }

}
