package com.example;


import java.util.Optional;

class LoginRepo {
    private UserRepo userRepo;
    private UserSessionRepo userSessionRepo;

    LoginRepo(UserRepo userRepo, UserSessionRepo userSessionRepo) {
        this.userRepo = userRepo;
        this.userSessionRepo = userSessionRepo;
    }

    Optional<UserSession> getUserSession(String username, String password) {
        Optional<User> maybeUser = userRepo.validate(new LoginCredentials(username, password));
        if(!maybeUser.isPresent()) {
            return Optional.empty();
        }

        return userSessionRepo.create(maybeUser.get());
    }
}
