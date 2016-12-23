package com.example;

import java.util.Optional;

public class FakeUserDataMapper implements UserDataMapper {
    Optional<User> findByUsername_returnValue;

    @Override
    public Optional<User> findByUsername(String username) {
        return findByUsername_returnValue;
    }

    @Override
    public Optional<User> validate(LoginCredentials credentials) {
        return null;
    }
}
