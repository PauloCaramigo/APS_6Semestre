package com.Interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("Layout.fxml"));
        Parent root = fxml.load();
        Scene display = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(display);
        primaryStage.show();
    }
}

