package org.politechnika.frontend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.politechnika.matlab.MatlabSessionFactory;
import org.politechnika.matlab.UrlMatlabConnector;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Slf4j
public class OptionsController implements Initializable {

    @FXML private Button engineSearchButton;
    @FXML private Button engineLoadButton;
    @FXML private TextField matlabEnginePath;
    @FXML private Label engineStatusLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final FileChooser.ExtensionFilter jarFilter = new FileChooser.ExtensionFilter(  "*.jar", "*.jar");
        engineSearchButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(jarFilter);
            Optional.ofNullable(fileChooser.showOpenDialog(null))
                    .ifPresent(file -> matlabEnginePath.setText(file.getPath()));
        });

        engineLoadButton.setOnAction(event -> {
            if (StringUtils.isNotBlank(matlabEnginePath.getText())) {
                log.info("Loading matlab engine.jar from file");
                engineStatusLabel.setText("Ładowanie...");
                UrlMatlabConnector connector = new UrlMatlabConnector(matlabEnginePath.getText());
                log.info("Engine loaded");
                MatlabSessionFactory.closeMatlabSession();
                MatlabSessionFactory.changeConnectorProvider(connector);
                engineStatusLabel.setText("OK!");
            }

        });

    }


}
