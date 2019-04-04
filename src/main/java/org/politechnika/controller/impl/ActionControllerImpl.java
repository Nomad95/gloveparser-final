package org.politechnika.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.controller.ActionController;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.frontend.main_controller.MainController;
import org.politechnika.report.CollectiveReportGenerator;
import org.politechnika.report.ReportGenerator;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ActionControllerImpl implements ActionController {

    private final List<ReportGenerator> reportGenerators;
    private final List<CollectiveReportGenerator> collectiveReportGenerators;

    @Override
    public void generate(List<AbstractDataFile> files, int timeIntervalInMillis) {
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
