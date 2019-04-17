package org.politechnika.file;

import lombok.AllArgsConstructor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

@AllArgsConstructor
public abstract class AbstractDataFile {

    private final String filePath;

    public Reader getReader() throws FileNotFoundException {
        return new FileReader(filePath);
    }

    public abstract String getFileType();
}
