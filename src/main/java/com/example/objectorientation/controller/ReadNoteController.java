package com.example.objectorientation.controller;

import com.example.objectorientation.HelloApplication;
import com.example.objectorientation.dao.NotesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ReadNoteController implements Initializable {

    public ReadNoteController() {

    }

    @FXML
    private Label headerLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label textLabel;
    @FXML
    private Button deleteButton;
    @FXML
    private Button closeButton;
    private NotesDAO notes = new NotesDAO();

    public void deleteNote(ActionEvent event) throws IOException {
        notes.deleteNoteById(1); // just for testing
    }

    public void closeNote(ActionEvent event) throws  IOException {
        HelloApplication a = new HelloApplication();
        a.changeScene("mainPage.fxml");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
    }
}