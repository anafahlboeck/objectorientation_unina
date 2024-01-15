package com.example.objectorientation.controller;

import com.example.objectorientation.HelloApplication;
import com.example.objectorientation.PathHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;



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
    private Button loginButton;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1)
    {
        //init DB
        try (Connection connection = DriverManager.getConnection(jdbcURL, user, password)) {
            HelloController.class.getClassLoader().getResourceAsStream("schema.sql");
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
        HelloApplication a = new HelloApplication();
        if(usernameField.getText().equals("admin") && passwordField.getText().equals("password")) {
            errorLabel.setText("Success");

            a.changeScene("mainPage.fxml");
        }
        else if(usernameField.getText().isEmpty() && passwordField.getText().isEmpty()) {
            errorLabel.setText("Please enter your data");
        }
        else {
            errorLabel.setText("Wrong credentials");
        }
    }
}
