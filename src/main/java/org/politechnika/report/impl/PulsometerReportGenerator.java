package org.politechnika.report.impl;

import org.politechnika.commons.Constants;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.report.ReportGenerator;

public class PulsometerReportGenerator implements ReportGenerator {
    @Override
    public void generate(AbstractDataFile dataFile) {
        //TODO: load data -> process and aggregate -> save to cache if needed for further processing -> save result to file (LAZY!)-> generate and save charts
    }

    @Override
    public boolean supports(String fileType) {
        return Constants.PULSOMETER.equals(fileType);
    }
}
