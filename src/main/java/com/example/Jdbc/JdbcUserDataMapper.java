package com.example.Jdbc;

import com.example.LoginCredentials;
import com.example.User;
import com.example.UserDataMapper;
import com.example.UserParams;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class JdbcUserDataMapper implements UserDataMapper {
    private JdbcTemplate jdbcTemplate;

    JdbcUserDataMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String queryString = "SELECT u.id, u.name, r.name AS role " +
                "FROM users AS u LEFT JOIN roles AS r ON u.role_id=r.id " +
                "WHERE u.name=?";
        try {
            User user = jdbcTemplate.queryForObject(
                    queryString,
                    (rs, i) -> new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("role")
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
        String queryString = "SELECT u.id, u.name, r.name AS role " +
                "FROM users AS u LEFT JOIN roles AS r ON u.role_id=r.id " +
                "WHERE u.name=? AND u.password=?";

        String username = credentials.getUsername();
        String password = credentials.getPassword();

        try {
            User user = jdbcTemplate.queryForObject(
                    queryString,
                    (rs, i) -> new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("role")
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
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users")
                .usingGeneratedKeyColumns("id");

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", userParams.getUsername());
        parameters.put("password", userParams.getPassword());

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource(parameters);

        try {
            int id = jdbcInsert.executeAndReturnKey(sqlParameterSource).intValue();

            User user = new User(id, userParams.getUsername(), "staff");

            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
