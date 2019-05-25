package org.politechnika.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.io.FilenameUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

@AllArgsConstructor
public abstract class AbstractDataFile {

    @Getter
    private final String filePath;

    public Reader getReader() throws FileNotFoundException {
        return new FileReader(filePath);
    }

    public abstract String getFileType();

    public String getFileExtension() {
        return FilenameUtils.getExtension(filePath);
    }
}
