package com.example.objectorientation.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.objectorientation.HelloApplication;
import com.example.objectorientation.service.AuthenticationService;
import javafx.beans.NamedArg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;

import java.io.IOException;

public class MainController implements Initializable {


    public MainController() {

    }
    HelloApplication a = new HelloApplication();
    @FXML
    private Button logoutButton;
    @FXML
    private Button openButton;
    @FXML
    private Button newNoteButton;
    @FXML
    private ListView<String> listView;

    private AuthenticationService authService = new AuthenticationService();


    public void userLogOut(ActionEvent event) throws IOException {
        logout();
    }

    public void openNote(ActionEvent event) throws IOException {
        openSelectedNote();
    }

    public void addNewNote(ActionEvent event) throws IOException {
        addNote();
    }

    private void logout() throws IOException {
        authService.logoutUser();
        a.changeScene("loginPage.fxml");
    }

    private void openSelectedNote() throws IOException {
        //retrieves selected message and opens readNotePage
        a.changeScene("readNotePage.fxml");
    }

    private void addNote() throws IOException {
        a.changeScene("newNotePage.fxml");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        String[] notes = {"note 1\t 14.03.2012", "note 2\t 14.05.2002", "note 3\t 25.02.2014"};
        listView.getItems().addAll(notes);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String selectedMessage = listView.getSelectionModel().getSelectedItem();
            }
        });
    }





}
