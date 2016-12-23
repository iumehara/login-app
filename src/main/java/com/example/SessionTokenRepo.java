package com.example;

import java.util.Optional;

class SessionTokenRepo {
    Optional<String> create(User user) {
        String usernameString = user.getUsername();
        return Optional.of("XXXSECRET_TOKEN" + usernameString + "XXX");
    }
}
