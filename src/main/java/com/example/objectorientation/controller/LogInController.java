package com.example.objectorientation.controller;

import com.example.objectorientation.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;


import java.io.IOException;

public class LogInController {

    public LogInController(){

    }

    @FXML
    private Button loginButton;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        HelloApplication a = new HelloApplication();
        if(username.getText().equals("admin") && password.getText().equals("password")) {
            errorLabel.setText("Success");

            a.changeScene("mainPage.fxml");
        }
        else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            errorLabel.setText("Please enter your data");
        }
        else {
            errorLabel.setText("Wrong credentials");
        }
    }


}
