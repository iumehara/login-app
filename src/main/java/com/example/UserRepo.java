package com.example;

import org.springframework.stereotype.Repository;

@Repository
class UserRepo {
    private UserDataMapper dataMapper;

    UserRepo(UserDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    User findByUsername(String username) {
        return dataMapper.findByUsername(username);
    }
}
