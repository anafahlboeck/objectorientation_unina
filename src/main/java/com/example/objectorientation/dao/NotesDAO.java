package com.example.objectorientation.dao;

import com.example.objectorientation.ApplicationState;
import com.example.objectorientation.model.Note;
import com.example.objectorientation.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class NotesDAO {

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_NOTE_ID = "note_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_HEADER = "header";
    public static final String COLUMN_TEXT = "text";
    private final ApplicationState state = ApplicationState.getInstance();

    private static final String GET_NOTE_BY_USER_ID = """
             SELECT * FROM notes WHERE user_id = ?
            """;

    public Collection<Note> getByUserId(User user) {

        try (Connection connection = DriverManager.getConnection(state.getJdbcUrl(), state.getDBUser(), state.getDBPassword())) {
            PreparedStatement prepStatement = connection.prepareStatement(GET_NOTE_BY_USER_ID);
            prepStatement.setLong(1, user.userId());
            ResultSet resultSet = prepStatement.executeQuery();
            Collection<Note> result = new ArrayList<>();
            while (resultSet.next()) {
                int userId = Integer.parseInt(resultSet.getString(COLUMN_USER_ID));
                int noteId = Integer.parseInt(resultSet.getString(COLUMN_NOTE_ID));
                LocalDate date = LocalDate.parse(resultSet.getString(COLUMN_DATE));
                String header = resultSet.getString(COLUMN_HEADER);
                String text = resultSet.getString(COLUMN_TEXT);
                Note note = new Note(noteId, userId, date, header, text);
                result.add(note);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}