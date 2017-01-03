package com.example;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class UserRepo {
    private UserDataMapper dataMapper;

    UserRepo(UserDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    Optional<User> findByUsername(String username) {
        return dataMapper.findByUsername(username);
    }

    Optional<User> validate(LoginCredentials credentials) {
        return dataMapper.validate(credentials);
    }

    Optional<User> create(UserParams userParams) {
        return null;
    }
}
