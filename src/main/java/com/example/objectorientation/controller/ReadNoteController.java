package com.example.objectorientation.controller;

import com.example.objectorientation.ApplicationState;
import com.example.objectorientation.Main;
import com.example.objectorientation.dao.NotesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class ReadNoteController implements Initializable {

    public ReadNoteController() {

    }

    Main a = new Main();
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane upperAnchorPane;
    @FXML
    private AnchorPane centerAnchorPane;
    @FXML
    private AnchorPane lowerAnchorPane;
    @FXML
    private Label headerLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label textLabel;
    
    final private ApplicationState applicationState = ApplicationState.getInstance();

    private NotesDAO notes = new NotesDAO();

    public void deleteNote(ActionEvent event) throws IOException {
        notes.deleteNoteById(applicationState.getActiveNote().noteId());
        applicationState.setActiveNote(null);
        a.changeScene("mainPage.fxml");
    }

    public void closeNote(ActionEvent event) throws  IOException {
        applicationState.setActiveNote(null);
        a.changeScene("mainPage.fxml");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        if (upperAnchorPane == null ||centerAnchorPane == null || lowerAnchorPane == null) {
            throw new IllegalStateException("AnchorPanes are not properly injected.");
        }

        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getVisualBounds().getWidth();
        double screenHeight = screen.getVisualBounds().getHeight();
        borderPane.setPrefHeight(screenHeight);
        borderPane.setPrefWidth(screenWidth);
        borderPane.setMaxHeight(screenHeight);
        borderPane.setMaxWidth(screenWidth);
        upperAnchorPane.setPrefHeight(screenHeight/4);
        upperAnchorPane.setPrefWidth(screenWidth);
        centerAnchorPane.setPrefHeight(screenHeight/2);
        centerAnchorPane.setPrefWidth(screenWidth);
        lowerAnchorPane.setPrefHeight(screenHeight/4);
        lowerAnchorPane.setPrefWidth(screenWidth);

        textLabel.setText(applicationState.getActiveNote().text());
        headerLabel.setText(applicationState.getActiveNote().header());
        dateLabel.setText(applicationState.getActiveNote().date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    }

}