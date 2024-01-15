package com.example.objectorientation.controller;

import com.example.objectorientation.HelloApplication;
import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import  javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

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

    public void cancelNote(ActionEvent event) throws IOException {
        cancelNoteAndExit();
    }

    public void saveNoteExit(ActionEvent event) throws IOException {
        saveNoteAndExit();
    }

    private void cancelNoteAndExit() throws IOException{
        a.changeScene("mainPage.fxml");
    }

    private void saveNoteAndExit() throws IOException {
        a.changeScene("mainPage.fxml");
    }
}
