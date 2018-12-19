package org.politechnika.controller.impl;

import lombok.RequiredArgsConstructor;
import org.politechnika.controller.ActionController;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.report.CollectiveReportGenerator;
import org.politechnika.report.ReportGenerator;

import java.util.List;
import java.util.Optional;

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

    private void generateSingleReports(List<AbstractDataFile> files) {
        for (AbstractDataFile file : files) {
            Optional<ReportGenerator> maybeReportGenerator = reportGenerators.stream()
                    .filter(generator -> generator.supports(file.getFileType())).findFirst();
            maybeReportGenerator.ifPresent(reportGenerator -> reportGenerator.generate(file));
            //TODO: do we wanna stop all processing after one error?
        }
    }

    private void generateCollectiveReports() {
        for (CollectiveReportGenerator generator : collectiveReportGenerators) {
            generator.generate();
        }
    }
}
