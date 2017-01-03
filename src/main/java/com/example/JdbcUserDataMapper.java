package com.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcUserDataMapper implements UserDataMapper {
    private JdbcTemplate jdbcTemplate;

    JdbcUserDataMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String queryString = "SELECT * FROM users where name=?";

        try {
            User user = jdbcTemplate.queryForObject(
                    queryString,
                    (rs, i) -> new User(
                            rs.getInt("id"),
                            rs.getString("name")
                    ),
                    username
            );
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> validate(LoginCredentials credentials) {
        String queryString = "SELECT * FROM users where name=? AND password=?";

        String username = credentials.getUsername();
        String password = credentials.getPassword();

        try {
            User user = jdbcTemplate.queryForObject(
                    queryString,
                    (rs, i) -> new User(
                            rs.getInt("id"),
                            rs.getString("name")
                    ),
                    username,
                    password
            );
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> create(UserParams userParams) {
        return null;
    }
}
