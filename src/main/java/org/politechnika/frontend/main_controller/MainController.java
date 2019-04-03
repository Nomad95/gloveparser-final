package org.politechnika.frontend.main_controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.politechnika.commons.Constants;
import org.politechnika.controller.ActionController;
import org.politechnika.controller.impl.ActionControllerImpl;
import org.politechnika.file.model.AbstractDataFile;
import org.politechnika.file.model.concrete_file.GloveDataFile;
import org.politechnika.file.model.concrete_file.PulsometerDataFile;
import org.politechnika.report.impl.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.parseInt;
import static java.util.Collections.unmodifiableList;
import static lombok.AccessLevel.PRIVATE;
import static org.politechnika.commons.Constants.FOLDER_DATE_FORMATTER;
import static org.politechnika.commons.Constants.MAX_MILLIS;
import static org.politechnika.commons.NumberCommons.tryGetIntValueFromString;

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
    @FXML private Button optionsButton;

    private Map<String, AbstractDataFile> filesMap = new HashMap<>(3);
    private ActionController actionController = new ActionControllerImpl(
            newArrayList(new PulsometerReportGenerator(), new KinectReportGenerator(), new GloveReportGenerator()),
            newArrayList(new InferenceReportGenerator(), new CorrelationReportGenerator(), new OverallReportGenerator()));

    @Getter
    @Setter(value = PRIVATE)
    private static int timeIntervalMillis = 1000;

    @Getter
    @Setter(value = PRIVATE)
    private static String destinationFolder;

    @Getter
    @Setter(value = PRIVATE)
    private static String destinationSubFolder;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter(  "*.csv", "*.csv");
        final FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter(  "*.txt", "*.txt");
        gloveSearchButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(csvFilter);
            Optional.ofNullable(fileChooser.showOpenDialog(null)).ifPresent(file -> {
                filesMap.put(Constants.GLOVE, new GloveDataFile(file.getPath()));
                gloveFilePathTextField.setText(file.getPath());
            });
        });

        pulsometerSearchButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(csvFilter, txtFilter);
            Optional.ofNullable(fileChooser.showOpenDialog(null)).ifPresent(file -> {
                filesMap.put(Constants.PULSOMETER, new PulsometerDataFile(file.getPath()));
                pulsometerFilePathTextField.setText(file.getPath());
            });
        });

        destinationFolderTextField.setOnAction(event -> {
            DirectoryChooser dirChooser = new DirectoryChooser();
            File directory = dirChooser.showDialog(null);
            //todo do sth with destination dir
        });

        millisTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                millisTextField.setText("1");
                return;
            }
            if (!newValue.matches("\\d*")) {
                newValue = newValue.replaceAll("[^\\d]", "");
                int intValue = tryGetIntValueFromString(newValue);
                millisTextField.setText(intValue > MAX_MILLIS ? "60000" : newValue);
            } else {
                int intValue = tryGetIntValueFromString(newValue);
                millisTextField.setText(intValue > MAX_MILLIS ? "60000" : newValue);
            }
            setTimeIntervalMillis(tryGetIntValueFromString(millisTextField.getText()));
        });

        generateReport.setDisable(true);
        destinationFolderTextField.setText("");
        destinationPathButton.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            Optional.ofNullable(directoryChooser.showDialog(null))
                    .ifPresent(file -> {
                        setDestinationFolder(file.getAbsolutePath());
                        destinationFolderTextField.setText(getDestinationFolder());
                        generateReport.setDisable(false);
                    });
        });

        generateReport.setOnAction(event -> {
            stopUi();
            List<AbstractDataFile> files = unmodifiableList(newArrayList(filesMap.values()));
            try {
                destinationSubFolder = destinationFolder
                        + ZonedDateTime.now(ZoneId.of("Europe/Warsaw"))
                        .toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern(FOLDER_DATE_FORMATTER));
                actionController.generate(files, parseInt(millisTextField.getText()));
            } finally {
                resumeUi();
            }
        });

        Glyph fontAwesome = new Glyph("FontAwesome", FontAwesome.Glyph.GEARS);
        fontAwesome.setFontSize(20);
        optionsButton.setGraphic(fontAwesome);
        optionsButton.setOnAction(event -> {
            try {
                URL resource = getClass().getResource("/fxml/optionsWindow.fxml");
                Parent optionsWindow = FXMLLoader.load(resource);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("Opcje");
                stage.setScene(new Scene(optionsWindow));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
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

}
