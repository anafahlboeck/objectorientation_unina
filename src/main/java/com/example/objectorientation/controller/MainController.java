package com.example.objectorientation.controller;

import com.example.objectorientation.ApplicationState;
import com.example.objectorientation.Main;
import com.example.objectorientation.dao.NotesDAO;
import com.example.objectorientation.model.Note;
import com.example.objectorientation.model.User;
import com.example.objectorientation.service.AuthenticationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    public MainController() {

    }
    Main a = new Main();
    @FXML
    private ListView<Note> listView;
    @FXML
    private Button sortButton;

    private AuthenticationService authService = new AuthenticationService();
    final private ApplicationState applicationState = ApplicationState.getInstance();
    private boolean sortedAscending = true;

    private User currentUser = authService.getCurrentUser();


    public void userLogOut(ActionEvent event) throws IOException {
        logout();
    }


    public void addNewNote(ActionEvent event) throws IOException {
        addNote();
    }

    public void changeOrder(ActionEvent event) throws IOException {
        if(sortedAscending)
        {
            sortButton.setText("Date ↓");
            sortedAscending = false;
            getNotes();

        }
        else
        {
            sortButton.setText("Date ↑");
            sortedAscending = true;
            getNotes();
        }
    }

    private void logout() throws IOException {
        authService.logoutUser();
        a.changeScene("loginPage.fxml");
    }


    private void addNote() throws IOException {
        a.changeScene("newNotePage.fxml");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        getNotes();
    }

    public void getNotes() {
        listView.getItems().clear();
        ArrayList<Note> notesList = new NotesDAO().getByUserId(currentUser, sortedAscending);

        listView.getItems().addAll(notesList);

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    applicationState.setActiveNote(newValue);
                    a.changeScene("readNotePage.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
