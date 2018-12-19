package org.politechnika.file.model.concrete_file;

import org.politechnika.commons.Constants;
import org.politechnika.file.model.AbstractDataFile;

public class PulsometerDataFile extends AbstractDataFile {

    public PulsometerDataFile(String filePath) {
        super(filePath);
    }

    @Override
    public String getFileType() {
        return Constants.PULSOMETER;
    }
}
