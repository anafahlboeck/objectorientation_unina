package com.example.objectorientation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {

        stg = stage;
        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getVisualBounds().getWidth();
        double screenHeight = screen.getVisualBounds().getHeight();
        stage.setWidth(screenWidth);
        stage.setHeight(screenHeight);

        stage.getIcons().add(new Image("file:src/main/resources/images/list.png"));

        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginPage.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

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
    }
}