package com.example.objectorientation.service;

import com.example.objectorientation.ApplicationState;
import com.example.objectorientation.model.User;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationService {

    private final static Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass().getPackageName());
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_USER_ID = "user_id";

    private final ApplicationState state = ApplicationState.getInstance();

    private Optional<User> getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(state.getJdbcUrl(), state.getDBUser(), state.getDBPassword());
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            List<User> users = new ArrayList<>();
            while (rs.next()) {
                String dbEmail = rs.getString(COLUMN_EMAIL);
                String dbPassword = rs.getString(COLUMN_PASSWORD);
                long dbUserId = rs.getLong(COLUMN_USER_ID);
                User currentUser = new User(dbUserId, dbEmail, dbPassword);
                users.add(currentUser);
            }

            int columnCount = users.size(); //search user 0 not found, 1 found, 1 < else
            if (columnCount == 0 || columnCount > 1) return Optional.empty();
            User validUserFromDatabase = users.get(0);
            return Optional.of(validUserFromDatabase);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void loginUser(String email, String password) {
        User loginUser = getUserByEmailAndPassword(email, password)
                .orElseThrow(() -> {
                    //TODO current error handling let the application crash -> what should we do if authentication fails?
                    return new IllegalArgumentException("Could not authenticate %s".formatted(email));
                });
        state.setSessionUser(loginUser);
        logger.log(Level.INFO, "Logging in User: %s".formatted(email));
    }

    public void logoutUser() {
        logger.log(Level.INFO, "Logging out User %s".formatted(state.getSessionUser().email()));
        state.logoutUser();
    }

    public boolean isAuthenticated(User user) {
        return state.authUser(user);
    }

}


