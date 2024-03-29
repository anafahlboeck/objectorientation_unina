package com.example.objectorientation.controller;

import com.example.objectorientation.Main;
import com.example.objectorientation.PathHandler;
import com.example.objectorientation.service.AuthenticationService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LogInController implements Initializable{

    public LogInController(){

    }

    private String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "admin";
    private String password = "password";
    private String query = "";

    @FXML
    private Label errorLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private AuthenticationService authService = new AuthenticationService();

    @FXML
    private AnchorPane leftAnchorPane;

    @FXML
    private AnchorPane rightAnchorPane;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        if (leftAnchorPane == null || rightAnchorPane == null) {
            throw new IllegalStateException("AnchorPanes are not properly injected.");
        }

        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getVisualBounds().getWidth();
        double screenHeight = screen.getVisualBounds().getHeight();

        leftAnchorPane.setPrefWidth(screenWidth / 2);
        leftAnchorPane.setPrefHeight(screenHeight);

        rightAnchorPane.setPrefWidth(screenWidth / 2);
        rightAnchorPane.setPrefHeight(screenHeight);

        //init DB
        try (Connection connection = DriverManager.getConnection(jdbcURL, user, password)) {
            MainController.class.getClassLoader().getResourceAsStream("schema.sql");
            String filePath = PathHandler.relativePath("src main resources schema.sql");
            Path of = Path.of(filePath);
            query = Files.readString(of);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        Main a = new Main();
        String email = usernameField.getText();
        String pw = passwordField.getText();
        if(email.isEmpty() && pw.isEmpty()) {
            errorLabel.setText("Please enter your data");
            return;
        }
        if(this.authService.loginUser(email, pw)){
            errorLabel.setText("Success");
            a.changeScene("mainPage.fxml");
        }
        else {
            errorLabel.setText("Wrong credentials");
        }
    }
}
