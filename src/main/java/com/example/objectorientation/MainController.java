package com.example.objectorientation;

import java.net.URL;
import java.util.ResourceBundle;

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

    @FXML
    private Button logoutButton;
    @FXML
    private ListView<String> listView;

    public void userLogOut(ActionEvent event) throws IOException {
        logout();
    }

    private void logout() throws IOException {
        HelloApplication a = new HelloApplication();
        a.changeScene("loginPage.fxml");
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
