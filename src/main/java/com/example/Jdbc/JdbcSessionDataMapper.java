package com.example.Jdbc;

import com.example.Session;
import com.example.SessionDataMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Optional;

@Repository
public class JdbcSessionDataMapper implements SessionDataMapper{
    private JdbcTemplate jdbcTemplate;

    public JdbcSessionDataMapper(JdbcTemplate jdbcTemplate) {
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

    @Override
    public Optional<Integer> validate(String token) {
        String queryString = "SELECT user_id FROM sessions WHERE token=?";

        try {
            Integer userId = jdbcTemplate.queryForObject(
                    queryString,
                    (rs, rowNum) -> rs.getInt("user_id"),
                    token
            );
            return Optional.of(userId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
