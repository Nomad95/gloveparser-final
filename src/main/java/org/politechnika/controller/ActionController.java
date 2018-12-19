package org.politechnika.controller;

import org.politechnika.file.model.AbstractDataFile;

import java.util.List;

public interface ActionController {

    void generate(List<AbstractDataFile> files, int timeIntervalInMillis);

}
