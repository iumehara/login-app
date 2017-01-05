package com.example;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepo {
    private UserDataMapper dataMapper;

    public UserRepo(UserDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public Optional<User> findByUsername(String username) {
        return dataMapper.findByUsername(username);
    }

    public Optional<User> validate(LoginCredentials credentials) {
        return dataMapper.validate(credentials);
    }

    public Optional<User> create(UserParams userParams) {
        Optional<Integer> maybeRoleId = dataMapper.findRoleIdByName(userParams.getRole());

        if(!maybeRoleId.isPresent()) return Optional.empty();

        Optional<Integer> maybeUserId = dataMapper.create(new UserData(userParams, maybeRoleId.get()));

        if(!maybeUserId.isPresent()) return Optional.empty();

        User user = new User(maybeUserId.get(), userParams.getUsername(), userParams.getRole());

        return Optional.of(user);
    }
}
