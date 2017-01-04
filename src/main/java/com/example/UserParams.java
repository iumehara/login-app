package com.example;

public class UserParams {
    private String username;
    private String password;

    @SuppressWarnings("unused")
    public UserParams() {}

    public UserParams(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @SuppressWarnings("WeakerAccess")
    public String getPassword() {
        return password;
    }

    @SuppressWarnings({"SimplifiableIfStatement"})
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserParams that = (UserParams) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserParams{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}