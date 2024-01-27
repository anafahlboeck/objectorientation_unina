package com.example.objectorientation.controller;

import com.example.objectorientation.Main;
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

    Main a = new Main();
    @FXML
    private Label headerLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label textLabel;

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
        headerLabel.setText(selectedNote.header());
        textLabel.setText(selectedNote.text());
        dateLabel.setText(selectedNote.date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

}