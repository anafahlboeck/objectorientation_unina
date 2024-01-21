package com.example.objectorientation.controller;

import com.example.objectorientation.HelloApplication;
import com.example.objectorientation.dao.NotesDAO;
import com.example.objectorientation.model.Note;
import com.example.objectorientation.model.User;
import com.example.objectorientation.service.AuthenticationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private ListView<Note> listView;

    private AuthenticationService authService = new AuthenticationService();

    private User currentUser = authService.getCurrentUser();


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
    public void initialize(URL arg0, ResourceBundle arg1) {
        ArrayList<Note> notesList = new NotesDAO().getByUserId(currentUser);

        listView.getItems().clear();
        listView.getItems().addAll(notesList);

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    openSelectedNotePage(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void openSelectedNotePage(Note selectedNote) throws IOException {

        File file = new File("src/main/resources/com/example/objectorientation/readNotePage.fxml");
        URL url = file.toURI().toURL();

        if (url != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            Parent root = loader.load();

            ReadNoteController readNoteController = loader.getController();
            readNoteController.initData(selectedNote);

            Scene scene = new Scene(root);
            Stage stage = (Stage) listView.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            System.err.println("FXML file not found.");
        }
    }
}
