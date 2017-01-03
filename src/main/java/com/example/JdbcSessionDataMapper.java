package com.example;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;
import java.util.Optional;

public class JdbcSessionDataMapper {
    private JdbcTemplate jdbcTemplate;

    JdbcSessionDataMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Session> create(int user_id, String token) {
        String queryString = "INSERT INTO sessions (user_id, token) VALUES (?, ?) ";
        Object[] params = {user_id, token};
        int[] types = {Types.INTEGER, Types.VARCHAR};

        try {
            int numberOfRowsInserted = jdbcTemplate.update(queryString, params, types);
            if (numberOfRowsInserted == 1) {
                return Optional.of(new Session(token, user_id));
            } else {
                return Optional.empty();
            }
        } catch(Exception e) {
            return Optional.empty();
        }
    }
}
