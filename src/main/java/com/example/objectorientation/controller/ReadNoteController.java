package com.example.objectorientation.controller;

import com.example.objectorientation.ApplicationState;
import com.example.objectorientation.HelloApplication;
import com.example.objectorientation.dao.NotesDAO;
import com.example.objectorientation.model.Note;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
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

    final private ApplicationState applicationState = ApplicationState.getInstance();
    final private HelloApplication a = new HelloApplication();


    public void deleteNote(ActionEvent event) throws IOException {
        notes.deleteNoteById(applicationState.getActiveNote().noteId());
        applicationState.setActiveNote(null);
        a.changeScene("mainPage.fxml");
        // just for testing
    }

    public void closeNote(ActionEvent event) throws  IOException {
        applicationState.setActiveNote(null);
        a.changeScene("mainPage.fxml");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        Note activeNote = applicationState.getActiveNote();
        headerLabel.setText(activeNote.header());
        String date = activeNote.date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        dateLabel.setText(date);
        textLabel.setText(activeNote.text());
    }
}