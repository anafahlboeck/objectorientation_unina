package com.example.objectorientation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText("Welcome to JavaFX Application!");

        try (Connection connection = DriverManager.getConnection(jdbcURL, user, password)) {
            HelloController.class.getClassLoader().getResourceAsStream("schema.sql");
            Path of = Path.of("C:\\Users\\AnaFahlböck\\Documents\\Uni\\NEAPEL\\oop\\object_orientation\\objectorientation\\src\\main\\resources\\schema.sql");
            query = Files.readString(of);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}