package com.example.objectorientation;

import com.example.objectorientation.model.Admin;
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
                new Admin(null, "admin", "admin",true),
                new User(null, "user@gmail.com", "password"),
                new User(null, "user1@gmail.com", "password1"),
                new User(null, "user2@hotmail.com", "password2")
        };

        try (Connection connection = DriverManager.getConnection(state.getJdbcUrl(), state.getDBUser(), state.getDBPassword())) {
                for (User testData : testUsers) {
                int userId = insertUserIfNotExists(connection, testData.email(), testData.password(), testData instanceof Admin);

                // Überprüfen, ob Benutzer existiert
                if (userId != -1) {
                    insertNotesIfNotExists(connection, userId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int insertUserIfNotExists(Connection connection, String email, String password, Boolean isAdmin) throws SQLException {
        String hashedPassword = PasswordHasher.hashPassword(password);

        String userInsertQuery = "INSERT INTO users(email, password, is_admin) VALUES (?, ?, ?) ON CONFLICT(email) DO NOTHING RETURNING user_id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(userInsertQuery)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setBoolean(3, isAdmin);

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
                    preparedStatement.setString(3, generateNoteHeader(i));
                    preparedStatement.setString(4, generateNoteText(i));

                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    private static String generateNoteHeader(int index) {
        return switch (index) {
            case 0 -> "Funny Note";
            case 1 -> "Shopping Reminder";
            case 2 -> "ToDo List";
            case 3 -> "Random Thought";
            case 4 -> "Grocery List";
            case 5 -> "Joke of the Day";
            case 6 -> "Important Task";
            default -> "Default Header";
        };
    }

    private static String generateNoteText(int index) {
        return switch (index) {
            case 0 -> "Why did the chicken join a band? Because it had the drumsticks!";
            case 1 -> "Don't forget to buy milk, eggs, and bread on your way home.";
            case 2 -> "ToDo List:\n1. Finish work project\n2. Call mom\n3. Exercise for 30 minutes";
            case 3 -> "I just realized that 'listen' and 'silent' have the same letters!";
            case 4 -> "Grocery List:\n- Apples\n- Chicken\n- Pasta\n- Spinach";
            case 5 -> "Why don't scientists trust atoms? Because they make up everything!";
            case 6 -> "Important Task: Complete the report for the meeting tomorrow.";
            default -> "Default Text";
        };
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

    public static void main(String[] args) {}
}
