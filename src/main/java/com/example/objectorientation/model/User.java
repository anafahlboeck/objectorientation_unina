package com.example.objectorientation.model;

import java.util.Objects;

public class User {
    protected final Long userId;
    protected final String email;
    protected final String password;

    public User(Long userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public Long userId() {
        return userId;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (User) obj;
        return Objects.equals(this.userId, that.userId) &&
               Objects.equals(this.email, that.email) &&
               Objects.equals(this.password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password);
    }

    @Override
    public String toString() {
        return "User[" +
               "userId=" + userId + ", " +
               "email=" + email + ", " +
               "password=" + password + ']';
    }

}