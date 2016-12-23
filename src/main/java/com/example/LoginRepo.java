package com.example;


import java.util.Optional;

class LoginRepo {
    private UserRepo userRepo;
    private SessionTokenRepo sessionTokenRepo;

    LoginRepo(UserRepo userRepo, SessionTokenRepo sessionTokenRepo) {
        this.userRepo = userRepo;
        this.sessionTokenRepo = sessionTokenRepo;
    }

    Optional<UserSession> getUserSession(String username, String password) {
        Optional<User> maybeUser = userRepo.validate(new LoginCredentials(username, password));
        if(!maybeUser.isPresent()) {
            return Optional.empty();
        }

        User user = maybeUser.get();

        Optional<String> maybeToken = sessionTokenRepo.create(user);

        if(!maybeToken.isPresent()) {
            return Optional.empty();
        }

        UserSession userSession = new UserSession(maybeToken.get(), user);

        return Optional.of(userSession);
    }
}
