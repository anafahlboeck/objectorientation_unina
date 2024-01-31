package com.example.objectorientation;

import com.example.objectorientation.controller.ReadNoteController;
import com.example.objectorientation.model.Note;
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

    public void openSelectedNotePage(Note selectedNote) throws IOException {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("readNotePage.fxml"));
        Scene scene = new Scene(loader.load());

            /*Screen screen = Screen.getPrimary();
            double screenWidth = screen.getVisualBounds().getWidth();
            double screenHeight = screen.getVisualBounds().getHeight();*/

        ReadNoteController readNoteController = loader.getController();
        readNoteController.initData(selectedNote);

        Screen screen = Screen.getPrimary();
            /*stage.setHeight(screenHeight);
            stage.setWidth(screenWidth);*/

        stg.setScene(scene);
        stg.show();
    }

    public static void main(String[] args) {
        launch();
    }
}