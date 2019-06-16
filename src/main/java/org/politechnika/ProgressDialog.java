package org.politechnika;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;

public class ProgressDialog {

    public static Alert alert;

    public static void showProgressAlert() {
        alert = new Alert(
                Alert.AlertType.INFORMATION,
                "Proszę czekać",
                ButtonType.CANCEL
        );
        alert.setTitle("Generowanie w usłudze Matlab");
        alert.setHeaderText("Proszę czekać");
        ProgressIndicator progressIndicator = new ProgressIndicator();
        alert.setGraphic(progressIndicator);

        alert.show();
        Button cancelBtn = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelBtn.setDisable(true);
    }
}