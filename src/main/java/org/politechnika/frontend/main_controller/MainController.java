package org.politechnika.frontend.main_controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import lombok.extern.slf4j.Slf4j;
import org.politechnika.commons.Constants;
import org.politechnika.controller.ActionController;
import org.politechnika.controller.impl.ActionControllerImpl;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.file.model.concrete_file.GloveDataFile;
import org.politechnika.report.impl.*;

import java.net.URL;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.parseInt;
import static java.util.Collections.unmodifiableList;
import static org.politechnika.commons.Constants.MILLIS_IN_MINUTE;
import static org.politechnika.commons.NumberUtils.tryGetIntValueFromString;

@Slf4j
public class MainController implements Initializable {

    @FXML private Button gloveSearchButton;
    @FXML private Button pulsometerSearchButton;
    @FXML private Button kinectSearchButton;
    @FXML private Button destinationPathButton;
    @FXML private Button generateReport;

    @FXML private TextField gloveFilePathTextField;
    @FXML private TextField pulsometerFilePathTextField;
    @FXML private TextField kinectFilePathTextField;
    @FXML private TextField destinationFolderTextField;
    @FXML private TextField millisTextField;

    private Map<String, AbstractDataFile> filesMap = new HashMap<>(3);
    private ActionController actionController = new ActionControllerImpl(
            newArrayList(new PulsometerReportGenerator(), new KinectReportGenerator(), new GloveReportGenerator()),
            newArrayList(new InferenceReportGenerator(), new CorrelationReportGenerator(), new OverallReportGenerator()));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter(  "*.csv", "*.csv");
        final FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter(  "Text Files", "*.txt");
        gloveSearchButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(csvFilter);
            Optional.ofNullable(fileChooser.showOpenDialog(null)).ifPresent(file -> {
                filesMap.put(Constants.GLOVE, new GloveDataFile(file.getPath()));
                gloveFilePathTextField.setText(file.getPath());
            });
        });

        millisTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                newValue = newValue.replaceAll("[^\\d]", "");
                int intValue = tryGetIntValueFromString(newValue);
                millisTextField.setText(intValue > MILLIS_IN_MINUTE ? "60000" : newValue);
            }
        });

        generateReport.setOnAction(event -> {
            stopUi();
            List<AbstractDataFile> files = unmodifiableList(newArrayList(filesMap.values()));
            actionController.generate(files, parseInt(millisTextField.getText()));
            resumeUi();
        });

        //TODO: rest of button loading files
    }

    private void stopUi() {
        gloveSearchButton.setDisable(true);
        pulsometerSearchButton.setDisable(true);
        kinectSearchButton.setDisable(true);
        destinationPathButton.setDisable(true);
        generateReport.setDisable(true);
    }

    private void resumeUi() {
        gloveSearchButton.setDisable(false);
        pulsometerSearchButton.setDisable(false);
        kinectSearchButton.setDisable(false);
        destinationPathButton.setDisable(false);
        generateReport.setDisable(false);
    }

    private void showErrorWindow() {
        //TODO: show message after generating error occurrs
    }

}
