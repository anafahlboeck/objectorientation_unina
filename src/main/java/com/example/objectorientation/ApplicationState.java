package com.example.objectorientation;
import com.example.objectorientation.model.User;
import com.example.objectorientation.model.Note;
import java.util.Objects;

public class ApplicationState {

    private static ApplicationState applicationState;
    private User sessionUser;
    private final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private final String POSTGRES_USER = "admin";
    private final String POSTGRES_PASSWORD = "password";

    private Note activeNote;

    private ApplicationState() {
    }

    public static ApplicationState getInstance() {
        if (Objects.equals(applicationState, null)) {
            applicationState = new ApplicationState();
            return applicationState;
        }
        return applicationState;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }

    public boolean authUser(User sessionUser) {
        if(Objects.equals(sessionUser,null)) return false;
        if (sessionUser.equals(this.sessionUser)) return true;
        return false;
    }

    public void logoutUser() {
        this.sessionUser = null;
    }

    public User getSessionUser() {
        return this.sessionUser;
    }
    public String getJdbcUrl() {
        return JDBC_URL;
    }

    public String getDBUser() {
        return POSTGRES_USER;
    }

    public String getDBPassword() {
        return POSTGRES_PASSWORD;
    }

    public void setActiveNote(Note note)
    {
        this.activeNote = note;
    }

    public Note getActiveNote(){
        return this.activeNote;
    }
}
