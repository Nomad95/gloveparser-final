package org.politechnika.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.frontend.MainController;
import org.politechnika.report.CollectiveReportGenerator;
import org.politechnika.report.ReportGenerator;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ActionControllerImpl {

    private final List<ReportGenerator> reportGenerators;
    private final List<CollectiveReportGenerator> collectiveReportGenerators;

    public void generate(List<AbstractDataFile> files) {
        generateSingleReports(files);
        //TODO: generate charts here or inside report generators?
        generateCollectiveReports();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void generateSingleReports(List<AbstractDataFile> files) {
        log.debug("Generating single reports...");
        new File(MainController.getDestinationSubFolder()).mkdirs();//TODO: warn if there are files in this dir?
        for (AbstractDataFile file : files) {
            Optional<ReportGenerator> maybeReportGenerator = reportGenerators.stream()
                    .filter(generator -> generator.supports(file.getFileType()))
                    .findFirst();
            maybeReportGenerator.ifPresent(reportGenerator -> reportGenerator.generate(file));
            //TODO: do we wanna stop all processing after one error or not?
        }
        log.debug("Single reports generated successfully");
    }

    private void generateCollectiveReports() {
        for (CollectiveReportGenerator generator : collectiveReportGenerators) {
            generator.generate();
        }
    }
}
