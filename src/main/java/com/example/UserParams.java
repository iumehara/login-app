package com.example;

public class UserParams {
    private String username;

    @SuppressWarnings("unused")
    public UserParams() {}

    UserParams(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserParams that = (UserParams) o;

        return username != null ? username.equals(that.username) : that.username == null;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserParams{" +
                "username='" + username + '\'' +
                '}';
    }
}
