package com.example.objectorientation.controller;

import com.example.objectorientation.HelloApplication;
import com.example.objectorientation.model.User;
import com.example.objectorientation.service.AuthenticationService;
import com.example.objectorientation.service.NoteManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NewNoteController {


    public NewNoteController() {

    }

    HelloApplication a = new HelloApplication();
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveExitButton;
    @FXML
    private TextField headerTextField;
    @FXML
    private TextField dateTextField;
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
}
