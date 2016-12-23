package com.example;

public class UserSession {
    private String token;
    private String username;

    UserSession(String token, User user) {
        this.token = token;
        this.username = user.getUsername();
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public String getToken() {
        return token;
    }

    @SuppressWarnings("unused")
    public String getUsername() {
        return username;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSession that = (UserSession) o;

        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        return username != null ? username.equals(that.username) : that.username == null;
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
