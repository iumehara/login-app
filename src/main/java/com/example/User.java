package com.example;

public class User {
    private String username;
    private String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public String getUsername() {
        return username;
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public String getPassword() {
        return password;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
