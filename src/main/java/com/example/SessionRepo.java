package com.example;

import java.util.Optional;

class SessionRepo {
    Optional<Session> create(User user) {
        String usernameString = user.getUsername();

        String token = "XXXSECRET_TOKEN" + usernameString + "XXX";

        return Optional.of(new Session(token, user));
    }
}
