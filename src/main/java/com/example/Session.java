package com.example;

public class Session {
    private String token;
    private int user_id;

    Session(String token, int user_id) {
        this.token = token;
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    @SuppressWarnings("unused")
    public int getUser_id() {
        return user_id;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (user_id != session.user_id) return false;
        return token != null ? token.equals(session.token) : session.token == null;
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + user_id;
        return result;
    }

    @Override
    public String toString() {
        return "Session{" +
                "token='" + token + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
