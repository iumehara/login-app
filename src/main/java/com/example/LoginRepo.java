package com.example;


import java.util.Optional;

class LoginRepo {
    private UserRepo userRepo;
    private SessionRepo sessionRepo;

    LoginRepo(UserRepo userRepo, SessionRepo sessionRepo) {
        this.userRepo = userRepo;
        this.sessionRepo = sessionRepo;
    }

    Optional<Session> getUserSession(String username, String password) {
        Optional<User> maybeUser = userRepo.validate(new LoginCredentials(username, password));
        if(!maybeUser.isPresent()) {
            return Optional.empty();
        }

        User user = maybeUser.get();

        Optional<String> maybeToken = sessionRepo.create(user);

        if(!maybeToken.isPresent()) {
            return Optional.empty();
        }

        Session session = new Session(maybeToken.get(), user);

        return Optional.of(session);
    }
}
