package com.example.objectorientation.controller;

import com.example.objectorientation.PathHandler;
import com.example.objectorientation.service.AuthenticationService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Label welcomeText;
    private String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";
    private String user = "admin";
    private String password = "password";
    private String query = "";

    private AuthenticationService authService = new AuthenticationService();

    @FXML
    private TextArea notesTextArea;

    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText("Welcome to JavaFX Application!");

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

    @FXML
    protected void loginUser() {
        this.authService.loginUser("user1@gmail.com", "password1");
    }

    @FXML
    protected void logoutUser() {
        this.authService.logoutUser();
    }
}