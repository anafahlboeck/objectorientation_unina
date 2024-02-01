package com.example.objectorientation.controller;

import com.example.objectorientation.Main;
import com.example.objectorientation.model.User;
import com.example.objectorientation.service.AuthenticationService;
import com.example.objectorientation.service.NoteManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class NewNoteController implements Initializable {


    public NewNoteController() {

    }

    Main a = new Main();

    @FXML
    private AnchorPane upperAnchorPane;
    @FXML
    private AnchorPane centerAnchorPane;
    @FXML
    private AnchorPane lowerAnchorPane;
    @FXML
    private TextField headerTextField;
    @FXML
    private Label dateLabel;
    @FXML
    private TextArea noteTextArea;

    private final AuthenticationService authService = new AuthenticationService();
    private final User currentUser = authService.getCurrentUser();

    public void cancelNote(ActionEvent event) throws IOException {
        cancelNoteAndExit();
    }

    public void saveNoteExit(ActionEvent event) throws IOException {
        String header = headerTextField.getText();
        System.out.println(header);
        String text = noteTextArea.getText();
        System.out.println(text);

        NoteManager noteManager = new NoteManager();
        noteManager.addNote(this.currentUser.userId(),headerTextField.getText(), noteTextArea.getText());
        saveNoteAndExit();
    }

    private void cancelNoteAndExit() throws IOException{
        a.changeScene("mainPage.fxml");
    }

    private void saveNoteAndExit() throws IOException {
        a.changeScene("mainPage.fxml");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        if (upperAnchorPane == null || centerAnchorPane == null || lowerAnchorPane == null) {
            throw new IllegalStateException("AnchorPanes are not properly injected.");
        }

        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getVisualBounds().getWidth();
        double screenHeight = screen.getVisualBounds().getHeight();
        upperAnchorPane.setPrefHeight(screenHeight/4);
        upperAnchorPane.setPrefWidth(screenWidth);
        centerAnchorPane.setPrefHeight(screenHeight/2);
        centerAnchorPane.setPrefWidth(screenWidth);
        lowerAnchorPane.setPrefHeight(screenHeight/4);
        lowerAnchorPane.setPrefWidth(screenWidth);

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateLabel.setText(date);

    }
}
