package org.politechnika.report;

import org.politechnika.file.model.AbstractDataFile;

public interface ReportGenerator {

    void generate(AbstractDataFile dataFile);

    boolean supports(String fileType);
}
