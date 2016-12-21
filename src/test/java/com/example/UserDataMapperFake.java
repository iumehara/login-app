package com.example;

public class UserDataMapperFake implements UserDataMapper {
    User findByUsername_returnValue;

    @Override
    public User findByUsername(String username) {
        return findByUsername_returnValue;
    }
}
