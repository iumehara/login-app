package com.example;

import java.util.List;
import java.util.Optional;

public interface UserDataMapper {
    List<User> all();

    Optional<User> findByUsername(String username);

    Optional<User> validate(LoginCredentials credentials);

    Optional<Integer> create(UserData userData);

    Optional<Integer> findRoleIdByName(String roleName);
}
