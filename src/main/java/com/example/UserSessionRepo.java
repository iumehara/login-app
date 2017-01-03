package com.example;

import java.util.Optional;

class UserSessionRepo {
    private SessionDataMapper sessionDataMapper;

    UserSessionRepo(SessionDataMapper sessionDataMapper) {
        this.sessionDataMapper = sessionDataMapper;
    }

    Optional<UserSession> create(User user) {
        String token = TokenGenerator.createRandom();

        Optional<Session> maybeSession = sessionDataMapper.create(user.getId(), token);

        if(!maybeSession.isPresent()) {
            return Optional.empty();
        }

        UserSession userSession = new UserSession(maybeSession.get(), user);
        return Optional.of(userSession);
    }
}
