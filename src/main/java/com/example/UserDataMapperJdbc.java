package com.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataMapperJdbc implements UserDataMapper {
    private JdbcTemplate jdbcTemplate;

    UserDataMapperJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByUsername(String username) {
        String queryString = "SELECT * FROM users where name=?";

        return jdbcTemplate.queryForObject(
                queryString,
                (rs, i) -> new User(
                        rs.getString("name"),
                        rs.getString("password")
                ),
                username
        );
    }
}
