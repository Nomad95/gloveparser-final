package org.politechnika.frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.politechnika.commons.Constants;
import org.politechnika.controller.ActionControllerImpl;
import org.politechnika.file.AbstractDataFile;
import org.politechnika.file.GloveDataFile;
import org.politechnika.file.KinectDataFile;
import org.politechnika.file.PulsometerDataFile;
import org.politechnika.matlab.StartMatlabInstanceTask;
import org.politechnika.report.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.unmodifiableList;
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

    @FXML private Label matlabStatusLabel;
    @FXML private Label progresLabel;

    private Map<String, AbstractDataFile> filesMap = new HashMap<>(3);
    private ActionControllerImpl actionController = new ActionControllerImpl(
            newArrayList(new PulsometerReportGenerator(), new KinectReportGenerator(), new GloveReportGenerator()),
            newArrayList(new InferenceReportGenerator(), new CorrelationReportGenerator(), new OverallReportGenerator()));

    private boolean matlabRunning = false;
    private boolean destinationFolderChosen = false;
    private boolean anyFileChosen = false;

    private static int timeIntervalMillis = 1000;
    private static String destinationFolder;
    private static String destinationSubFolder;

    public static int getTimeIntervalMillis() {
        return MainController.timeIntervalMillis;
    }

    public static String getDestinationFolder() {
        return MainController.destinationFolder;
    }

    public static String getDestinationSubFolder() {
        return MainController.destinationSubFolder;
    }

    private static void setTimeIntervalMillis(int timeIntervalMillis) {
        MainController.timeIntervalMillis = timeIntervalMillis;
    }

    private static void setDestinationFolder(String destinationFolder) {
        MainController.destinationFolder = destinationFolder;
    }

    private static void setDestinationSubFolder(String destinationSubFolder) {
        MainController.destinationSubFolder = destinationSubFolder;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tryStartMatlab();
        initFileButtons();
        initGenerationControls();
        prepareOptionsPane();
    }

    private void tryStartMatlab() {
        matlabStatusLabel.setText("Uruchamianie usługi matlab...");
        StartMatlabInstanceTask startMatlabTask = new StartMatlabInstanceTask();
        startMatlabTask.setOnSucceeded(e -> {
            matlabStatusLabel.setText("Usługa matlab jest uruchomiona");
            matlabRunning = true;
            checkIfGenerateButtonShouldBeEnabled();
        });
        startMatlabTask.setOnFailed(e -> {
            matlabStatusLabel.setText("Nie udało się uruchomić usługi matlab");
            matlabRunning = false;
            checkIfGenerateButtonShouldBeEnabled();
        });
        Thread thread = new Thread(startMatlabTask);
        thread.setDaemon(true);
        thread.start();
    }

    private void initFileButtons() {
        final FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter(  "*.csv", "*.csv");
        final FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter(  "*.txt", "*.txt");
        gloveSearchButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(csvFilter);
            Optional.ofNullable(fileChooser.showOpenDialog(null)).ifPresent(file -> {
                filesMap.put(Constants.GLOVE, new GloveDataFile(file.getPath()));
                gloveFilePathTextField.setText(file.getPath());
                anyFileChosen = true;
                checkIfGenerateButtonShouldBeEnabled();
            });
        });

        pulsometerSearchButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(csvFilter, txtFilter);
            Optional.ofNullable(fileChooser.showOpenDialog(null)).ifPresent(file -> {
                filesMap.put(Constants.PULSOMETER, new PulsometerDataFile(file.getPath()));
                pulsometerFilePathTextField.setText(file.getPath());
                anyFileChosen = true;
                checkIfGenerateButtonShouldBeEnabled();
            });
        });

        kinectSearchButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(csvFilter);
            Optional.ofNullable(fileChooser.showOpenDialog(null)).ifPresent(file -> {
                filesMap.put(Constants.KINECT, new KinectDataFile(file.getPath()));
                kinectFilePathTextField.setText(file.getPath());
                anyFileChosen = true;
                checkIfGenerateButtonShouldBeEnabled();
            });
        });
    }

    private void initGenerationControls() {
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

        destinationFolderTextField.setOnAction(event -> {
            DirectoryChooser dirChooser = new DirectoryChooser();
            File directory = dirChooser.showDialog(null);
            //todo do sth with destination dir
        });

        generateReport.setDisable(true);
        destinationFolderTextField.setText("");
        destinationPathButton.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            Optional.ofNullable(directoryChooser.showDialog(null))
                    .ifPresent(file -> {
                        setDestinationFolder(file.getAbsolutePath());
                        destinationFolderTextField.setText(getDestinationFolder());
                        destinationFolderChosen = true;
                        checkIfGenerateButtonShouldBeEnabled();
                    });
        });

        generateReport.setOnMousePressed(e -> {
            progresLabel.setText("Twra generowanie raportów, proszę czekać");
        });

        generateReport.setOnAction(event -> {
            stopUi();
            List<AbstractDataFile> files = unmodifiableList(newArrayList(filesMap.values()));
            try {
                destinationSubFolder = destinationFolder
                        + ZonedDateTime.now(ZoneId.of("Europe/Warsaw"))
                        .toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern(FOLDER_DATE_FORMATTER));
                actionController.generate(files);
            } finally {
                resumeUi();
                progresLabel.setText("Raporty zostały wygenerowane");
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

    private void checkIfGenerateButtonShouldBeEnabled() {
        generateReport.setDisable(!(matlabRunning && destinationFolderChosen && anyFileChosen));
    }

    private void prepareOptionsPane() {
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
}
