package com.example.exercise1imageeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("S4-Exercise1(ImageViewer)-MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);

        stage.setTitle("Image Viewer");
        stage.setScene(scene);
        stage.show();
        primaryStage = stage;
    }

    public static void main(String[] args) {
        launch();
    }
}