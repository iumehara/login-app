package com.exampleTest.session;

import com.example.session.Session;
import com.example.session.SessionDataMapper;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class FakeSessionDataMapper implements SessionDataMapper {
    public Optional<Session> create_returnValue;
    public int create_param_user_id;
    @Override
    public Optional<Session> create(int user_id, String token) {
        create_param_user_id = user_id;
        return create_returnValue;
    }

    Optional<Integer> validate_returnValue;
    String validate_param_token;
    @Override
    public Optional<Integer> validate(String token) {
        validate_param_token = token;
        return validate_returnValue;
    }
}
