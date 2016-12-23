package com.example;

import java.util.Optional;

class SessionRepo {
    private SessionDataMapper dataMapper;

    SessionRepo(SessionDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    Optional<Session> create(User user) {
        String usernameString = user.getUsername();

        String token = "XXXSECRET_TOKEN" + usernameString + "XXX";

        return dataMapper.create(user.getId(), token);
    }
}
