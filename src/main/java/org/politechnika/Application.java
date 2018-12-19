package org.politechnika;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = getClass().getResource("/fxml/mainWindow.fxml");
        Parent root = FXMLLoader.load(resource);

        Scene scene = new Scene(root, 480, 480);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Parser danych");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
