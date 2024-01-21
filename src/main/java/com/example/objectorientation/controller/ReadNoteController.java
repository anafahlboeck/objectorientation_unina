package com.example.objectorientation.controller;

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
import java.util.ResourceBundle;


public class ReadNoteController implements Initializable {

    public ReadNoteController() {

    }

    HelloApplication a = new HelloApplication();
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

    private Note selectedNote;
    private NotesDAO notes = new NotesDAO();

    public void deleteNote(ActionEvent event) throws IOException {
        notes.deleteNoteById(this.selectedNote.noteId());
        a.changeScene("mainPage.fxml");
    }

    public void closeNote(ActionEvent event) throws  IOException {
        a.changeScene("mainPage.fxml");
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
    }

    public void initData(Note selectedNote) {
        this.selectedNote = selectedNote;
        headerLabel.setText("Header: " + selectedNote.header());
        textLabel.setText("Text: " + selectedNote.text());
        dateLabel.setText("Date: " + selectedNote.date());
    }

}