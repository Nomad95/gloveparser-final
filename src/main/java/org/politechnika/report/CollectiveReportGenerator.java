package org.politechnika.report;

import org.politechnika.file.AbstractDataFile;

import java.util.List;

public interface CollectiveReportGenerator {

    void generate(List<AbstractDataFile> files);

}
