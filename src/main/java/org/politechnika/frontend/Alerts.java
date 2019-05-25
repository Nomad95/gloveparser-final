package org.politechnika.frontend;

import javafx.scene.control.Alert;

public enum Alerts {
    I;

    public static final String PULSOMETER_PARSING_ERR = "Plik danych pulsometru jest niepoprawny lub zawiera niewłaściwe dane";
    public static final String GLOVE_PARSING_ERR = "Plik danych rękawicy 5DT jest niepoprawny lub zawiera niewłaściwe dane";
    public static final String KINECT_PARSING_ERR = "Plik danych z Kinect jest niepoprawny lub zawiera niewłaściwe dane";
    public static final String FILE_NOT_FOUND = "Nie można odnaleźć pliku:\n";

    public void raiseError(final String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText("");
        alert.setContentText(message);

        alert.showAndWait();
    }
}
