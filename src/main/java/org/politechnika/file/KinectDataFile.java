package org.politechnika.file;

import org.politechnika.commons.Constants;

public class KinectDataFile extends AbstractDataFile {

    public KinectDataFile(String filePath) {
        super(filePath);
    }

    @Override
    public String getFileType() {
        return Constants.KINECT;
    }
}
