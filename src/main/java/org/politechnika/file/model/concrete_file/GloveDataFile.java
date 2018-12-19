package org.politechnika.file.model.concrete_file;

import org.politechnika.commons.Constants;
import org.politechnika.file.model.AbstractDataFile;

public class GloveDataFile extends AbstractDataFile {

    public GloveDataFile(String filePath) {
        super(filePath);
    }

    @Override
    public String getFileType() {
        return Constants.GLOVE;
    }
}
