package com.example.objectorientation.model;

import java.time.LocalDate;

public record Note(int noteId, int userId, LocalDate date, String header, String text) {

    @Override
    public String toString() {
        return header + ", Text: " + text + ", Date: " + date;
    }
}