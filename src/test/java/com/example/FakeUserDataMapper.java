package com.example;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class FakeUserDataMapper implements UserDataMapper {
    Optional<User> findByUsername_returnValue;
    String findByUsername_param_username;
    @Override
    public Optional<User> findByUsername(String username) {
        findByUsername_param_username = username;
        return findByUsername_returnValue;
    }

    Optional<User> validate_returnValue;
    LoginCredentials validate_param_credentials;
    @Override
    public Optional<User> validate(LoginCredentials credentials) {
        validate_param_credentials = credentials;
        return validate_returnValue;
    }

    Optional<Integer> create_returnValue;
    UserData create_param_userData;
    @Override
    public Optional<Integer> create(UserData userData) {
        create_param_userData = userData;
        return create_returnValue;
    }

    Optional<Integer> findRoleIdByName_returnValue;
    String findRoleIdByName_param_roleName;
    @Override
    public Optional<Integer> findRoleIdByName(String roleName) {
        findRoleIdByName_param_roleName = roleName;
        return findRoleIdByName_returnValue;
    }
}
