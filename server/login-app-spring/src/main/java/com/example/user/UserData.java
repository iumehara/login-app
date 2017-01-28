package com.example.user;

public class UserData {
    private String username;
    private String password;
    private int roleId;

    @SuppressWarnings("unused")
    public UserData() {}

    public UserData(UserParams userParams, Integer roleId) {
        this.username = userParams.getUsername();
        this.password = userParams.getPassword();
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    @SuppressWarnings("WeakerAccess")
    public String getPassword() {
        return password;
    }

    public int getRoleId() {
        return roleId;
    }

    @SuppressWarnings("SimplifiableIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (roleId != userData.roleId) return false;
        if (username != null ? !username.equals(userData.username) : userData.username != null) return false;
        return password != null ? password.equals(userData.password) : userData.password == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + roleId;
        return result;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
