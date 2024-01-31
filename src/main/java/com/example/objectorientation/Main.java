package com.example.objectorientation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.objectorientation.TestDataGenerator.insertTestDataIfNotExists;

public class Main extends Application {

    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {

        stg = stage;

        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginPage.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1512, 800);
        stage.setTitle("Scrivimi!");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException {

        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);

    }

    public static void main(String[] args) {
        launch();
        insertTestDataIfNotExists();
    }
}