package com.example;

import java.util.Optional;

public interface SessionDataMapper {
    Optional<Session> create(int user_id, String token);
    Optional<Integer> validate(String token);
}
