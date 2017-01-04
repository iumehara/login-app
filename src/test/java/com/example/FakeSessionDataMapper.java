package com.example;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class FakeSessionDataMapper implements SessionDataMapper {
    Optional<Session> create_returnValue;
    int create_param_user_id;

    @Override
    public Optional<Session> create(int user_id, String token) {
        create_param_user_id = user_id;
        return create_returnValue;
    }
}