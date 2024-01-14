package com.example.objectorientation.model;

import java.time.LocalDate;

public record Note(int noteId, int userId, LocalDate date, String header, String text) {
}