package com.example.objectorientation;

import com.example.objectorientation.model.User;
import com.example.objectorientation.security.PasswordHasher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TestDataGenerator {

    private static final ApplicationState state = ApplicationState.getInstance();

    public static void insertTestDataIfNotExists() {
        User[] testUsers = {
                new User(null, "admin", "admin"),
                new User(null, "user1@gmail.com", "password1"),
                new User(null, "user2@hotmail.com", "password2")
        };

        try (Connection connection = DriverManager.getConnection(state.getJdbcUrl(), state.getDBUser(), state.getDBPassword())) {
            for (User testData : testUsers) {
                int userId = insertUserIfNotExists(connection, testData.email(), testData.password());

                // Überprüfen, ob Benutzer existiert
                if (userId != -1) {
                    insertNotesIfNotExists(connection, userId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int insertUserIfNotExists(Connection connection, String email, String password) throws SQLException {
        String hashedPassword = PasswordHasher.hashPassword(password);

        String userInsertQuery = "INSERT INTO users(email, password) VALUES (?, ?) ON CONFLICT(email) DO NOTHING RETURNING user_id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(userInsertQuery)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashedPassword);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt("user_id") : -1;
            }
        }
    }

    private static void insertNotesIfNotExists(Connection connection, int userId) throws SQLException {
        if (!userHasNotes(connection, userId)) {
            String noteInsertQuery = "INSERT INTO notes(user_id, date, header, text) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(noteInsertQuery)) {
                for (int i = 0; i < 7; i++) {
                    preparedStatement.setInt(1, userId);
                    preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDate.now().plusDays(i)));
                    preparedStatement.setString(3, "Note " + (i + 1) + " Header");
                    preparedStatement.setString(4, "Note " + (i + 1) + " Text");

                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    private static boolean userHasNotes(Connection connection, int userId) throws SQLException {
        String countNotesQuery = "SELECT COUNT(*) AS note_count FROM notes WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(countNotesQuery)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && resultSet.getInt("note_count") > 0;
            }
        }
    }

    public static void main(String[] args) {
        insertTestDataIfNotExists();
    }
}
