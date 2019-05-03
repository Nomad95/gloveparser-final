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
public class CorrelationReportGenerator implements CollectiveReportGenerator {

    private final FileWriter fileWriter;

    @Override
    public void generate(List<AbstractDataFile> files) {
        StringBuilder sb = new StringBuilder();
        for (AbstractDataFile file : files) {
            if (Constants.GLOVE.equals(file.getFileType())) {
                sb.append("-------------- Tabela korelacji dla palców lewej ręki 5DT (kciuk, wskazujący, środkowy, serdeczny, mały)--------------")
                        .append(NEWLINE).append(NEWLINE)
                        .append(LoadingStringCache.get(LEFT_HAND_CORRELATIONS))
                        .append(NEWLINE)
                        .append("-------------- Tabela korelacji dla palców prawej ręki 5DT (kciuk, wskazujący, środkowy, serdeczny, mały)--------------")
                        .append(NEWLINE).append(NEWLINE)
                        .append(LoadingStringCache.get(RIGHT_HAND_CORRELATIONS))
                        .append(NEWLINE);
            }
            else if (Constants.KINECT.equals(file.getFileType())) {
                sb.append("-------------- Tabela korelacji dla Kinect - normy --------------")
                        .append(NEWLINE)
                        .append("[ SPINE_BASE, SPINE_MID, NECK, HEAD, SHOULDER_LEFT, SHOULDER_RIGHT, ELBOW_LEFT, ELBOW_RIGHT, WRIST_LEFT, WRIST_RIGHT, HAND_LEFT, HAND_RIGHT, HIP_LEFT, HIP_RIGHT, KNEE_LEFT, KNEE_RIGHT, ANKLE_LEFT, ANKLE_RIGHT, FOOT_LEFT, FOOT_RIGHT, SPINE_SHOULDER, HAND_TIP_LEFT, HAND_TIP_RIGHT, THUMB_LEFT, THUMB_RIGHT ]")
                        .append(NEWLINE).append(NEWLINE)
                        .append(LoadingStringCache.get(KINECT_CORRELATIONS))
                        .append(NEWLINE);
            }
        }
        String report = sb.toString();
        if (report.isEmpty()) return;

        try {
            fileWriter.saveTextFile(report, "/correlaion.txt");
        } catch (ParserWriteFileException e) {
            log.error("Could not save correlation report {}!", e.getMessage());
        }
        log.debug("Correlation report was generated");
    }

}
