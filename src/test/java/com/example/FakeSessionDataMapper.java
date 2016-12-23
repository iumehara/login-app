package com.example;

import java.util.Optional;

public class FakeSessionDataMapper implements SessionDataMapper {
    Optional<Session> create_returnValue;
    int create_param_user_id;
    String create_param_token;

    @Override
    public Optional<Session> create(int user_id, String token) {
        create_param_user_id = user_id;
        create_param_token = token;
        return create_returnValue;
    }
}
