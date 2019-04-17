package org.politechnika.file;

import org.politechnika.commons.Constants;

public class GloveDataFile extends AbstractDataFile {

    public GloveDataFile(String filePath) {
        super(filePath);
    }

    @Override
    public String getFileType() {
        return Constants.GLOVE;
    }
}
