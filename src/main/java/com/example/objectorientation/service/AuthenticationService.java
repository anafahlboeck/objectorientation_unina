package com.example.objectorientation.service;

import com.example.objectorientation.ApplicationState;
import com.example.objectorientation.model.User;
import com.example.objectorientation.security.PasswordHasher;

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
    public static final String COLUMN_PASSWORD_HASH = "password";
    public static final String COLUMN_USER_ID = "user_id";

    private final ApplicationState state = ApplicationState.getInstance();

    private Optional<User> getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = DriverManager.getConnection(state.getJdbcUrl(), state.getDBUser(), state.getDBPassword());
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            List<User> users = new ArrayList<>();
            while (rs.next()) {
                String dbEmail = rs.getString(COLUMN_EMAIL);
                String dbPasswordHash = rs.getString(COLUMN_PASSWORD_HASH);
                long dbUserId = rs.getLong(COLUMN_USER_ID);
                User currentUser = new User(dbUserId, dbEmail, dbPasswordHash);
                users.add(currentUser);
            }

            int columnCount = users.size(); //search user 0 not found, 1 found, 1 < else
            if (columnCount == 0 || columnCount > 1) return Optional.empty();

            User validUserFromDatabase = users.get(0);
            String hashPassword = PasswordHasher.hashPassword(password);
            String dbPassword = validUserFromDatabase.password();

            if (hashPassword != null && hashPassword.equals(dbPassword)) {
                return Optional.of(validUserFromDatabase);
            } else {
                return Optional.empty(); // Passwörter stimmen nicht überein
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean loginUser(String email, String password) {

        Optional<User> loginUser = getUserByEmailAndPassword(email, password);
        if(loginUser.isEmpty()) {
            return false;
        }
        else {
            state.setSessionUser(loginUser.get());
            logger.log(Level.INFO, "Logging in User: %s".formatted(email));
            return true;
        }

    }

    public void logoutUser() {
        logger.log(Level.INFO, "Logging out User %s".formatted(state.getSessionUser().email()));
        state.logoutUser();
    }

    public boolean isAuthenticated(User user) {
        return state.authUser(user);
    }
}
