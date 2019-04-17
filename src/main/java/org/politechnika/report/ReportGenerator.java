package org.politechnika.report;

import org.politechnika.file.AbstractDataFile;

public interface ReportGenerator {

    void generate(AbstractDataFile dataFile);

    boolean supports(String fileType);
}
