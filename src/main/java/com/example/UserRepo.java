package com.example;

class UserRepo {
    UserRepo() {}

    User findByUsername(String username) {
        return new User("adam", "secret");
    }
}
