package com.example;

import java.util.Optional;

class UserSessionRepo {
    private SessionDataMapper dataMapper;

    UserSessionRepo(SessionDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    Optional<UserSession> create(User user) {
        String usernameString = user.getUsername();

        String token = "XXXSECRET_TOKEN" + usernameString + "XXX";

        Optional<Session> maybeSession = dataMapper.create(user.getId(), token);

        if(!maybeSession.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(new UserSession(maybeSession.get().getToken(), user));
    }
}
