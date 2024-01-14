package com.example.objectorientation.service;

import com.example.objectorientation.ApplicationState;
import com.example.objectorientation.model.Note;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NoteManager {

    public static final String COLUMN_NOTE_ID = "note_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_HEADER = "header";
    public static final String COLUMN_TEXT = "text";
    public final ApplicationState state = ApplicationState.getInstance();

    public List<Note> getAllNotes(int userId) {
        List<Note> notes = new ArrayList<>();

        String sql = "SELECT * FROM notes WHERE user_id = ?";

        try (Connection connection = DriverManager.getConnection(state.getJdbcUrl(), state.getDBUser(), state.getDBPassword());
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int noteId = resultSet.getInt(COLUMN_NOTE_ID);
                    LocalDate date = resultSet.getDate(COLUMN_DATE).toLocalDate();
                    String header = resultSet.getString(COLUMN_HEADER);
                    String text = resultSet.getString(COLUMN_TEXT);

                    Note note = new Note(noteId, userId, date, header, text);
                    notes.add(note);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notes;
    }
}
