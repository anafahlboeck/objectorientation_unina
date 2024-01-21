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

public class NotesDAO {

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_NOTE_ID = "note_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_HEADER = "header";
    public static final String COLUMN_TEXT = "text";
    private final ApplicationState state = ApplicationState.getInstance();

    private static final String GET_NOTE_BY_USER_ID = """
             SELECT * FROM notes WHERE user_id = ? ORDER BY date %s
            """;

    private static final String DELETE_NOTE_BY_ID = """
             DELETE FROM notes WHERE note_id = ?
            """;

    public ArrayList<Note> getByUserId(User user, boolean ascending) {
        String sortOrder = ascending ? "ASC" : "DESC";
        String query = String.format(GET_NOTE_BY_USER_ID, sortOrder);
        System.out.println(query);

        try (Connection connection = DriverManager.getConnection(state.getJdbcUrl(), state.getDBUser(), state.getDBPassword())) {
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.setLong(1, user.userId());
            ResultSet resultSet = prepStatement.executeQuery();
            ArrayList<Note> result = new ArrayList<>();
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

    public void deleteNoteById(int noteId) {
        try (Connection connection = DriverManager.getConnection(state.getJdbcUrl(), state.getDBUser(), state.getDBPassword())) {
            PreparedStatement prepStatement = connection.prepareStatement(DELETE_NOTE_BY_ID);
            prepStatement.setInt(1, noteId);
            int affectedRows = prepStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new RuntimeException("Note with ID " + noteId + " not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}