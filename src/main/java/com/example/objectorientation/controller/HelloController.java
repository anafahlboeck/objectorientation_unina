package com.example.objectorientation.controller;

import com.example.objectorientation.model.Note;
import com.example.objectorientation.service.AuthenticationService;
import com.example.objectorientation.service.NoteManager;
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
import java.util.List;

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

    @FXML
    protected void onListButtonClick() {
        NoteManager noteManager = new NoteManager();

        List<Note> user1Notes = noteManager.getAllNotes(1);

        StringBuilder notesText = new StringBuilder();

        for (Note note : user1Notes) {
            notesText.append("Note ID: ").append(note.noteId())
                    .append(", User ID: ").append(note.userId())
                    .append(", Date: ").append(note.date())
                    .append(", Header: ").append(note.header())
                    .append(", Text: ").append(note.text())
                    .append("\n");
        }

        notesTextArea.setText(notesText.toString());
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