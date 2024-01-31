package com.example.objectorientation.model;

public class Admin extends User {
    private final boolean isAdmin;

    public Admin(Long userId, String email, String password, boolean isAdmin) {
        super(userId, email, password);
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "Admin[" +
               "userId=" + userId + ", " +
               "email=" + email + ", " +
               "password=" + password + ", " +
               "isAdmin=" + isAdmin + ']';
    }
}

