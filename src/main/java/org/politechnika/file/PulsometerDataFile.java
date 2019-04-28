package org.politechnika.file;

import org.politechnika.commons.Constants;

public class PulsometerDataFile extends AbstractDataFile {

    public PulsometerDataFile(String filePath) {
        super(filePath);
    }

    @Override
    public String getFileType() {
        return Constants.PULSOMETER;
    }
}
