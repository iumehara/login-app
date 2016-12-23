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

        return sessionRepo.create(maybeUser.get());
    }
}
