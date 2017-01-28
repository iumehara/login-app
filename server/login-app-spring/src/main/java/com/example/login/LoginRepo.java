package com.example.login;


import com.example.user.User;
import com.example.user.UserRepo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginRepo {
    private UserRepo userRepo;
    private UserSessionRepo userSessionRepo;

    public LoginRepo(UserRepo userRepo, UserSessionRepo userSessionRepo) {
        this.userRepo = userRepo;
        this.userSessionRepo = userSessionRepo;
    }

    public Optional<UserSession> getUserSession(String username, String password) {
        Optional<User> maybeUser = userRepo.validate(new LoginCredentials(username, password));
        if(!maybeUser.isPresent()) {
            return Optional.empty();
        }

        return userSessionRepo.create(maybeUser.get());
    }
}
