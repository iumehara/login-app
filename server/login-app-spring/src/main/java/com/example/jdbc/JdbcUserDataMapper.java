package com.example.jdbc;

import com.example.login.LoginCredentials;
import com.example.security.Encoder;
import com.example.user.User;
import com.example.user.UserData;
import com.example.user.UserDataMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserDataMapper implements UserDataMapper {
    private JdbcTemplate jdbcTemplate;

    public JdbcUserDataMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> all() {
        String queryString = "SELECT u.id, u.name, r.name AS role " +
                "FROM users AS u LEFT JOIN roles AS r ON u.role_id=r.id";

        try {
            return jdbcTemplate.query(
                    queryString,
                    (rs, i) -> new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("role")
                    )
            );
        } catch (Exception e) {
            return Collections.emptyList();
        }
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
        String username = credentials.getUsername();
        Optional<UserData> maybeUserData = findUserDataByUsername(username);
        if (!maybeUserData.isPresent()) return Optional.empty();

        String password = credentials.getPassword();
        String encodedPassword = maybeUserData.get().getPassword();
        boolean matches = new Encoder().matches(password, encodedPassword);
        if (!matches) return Optional.empty();

        String queryString = "SELECT u.id, u.name, u.password, r.name AS role " +
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
    public Optional<Integer> create(UserData userData) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users")
                .usingGeneratedKeyColumns("id");

        String password = userData.getPassword();
        if (password == null) return Optional.empty();
        String encodedPassword = new Encoder().encode(password);
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", userData.getUsername());
        parameters.put("password", encodedPassword);
        parameters.put("role_id", userData.getRoleId());

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource(parameters);

        try {
            Integer id = jdbcInsert.executeAndReturnKey(sqlParameterSource).intValue();
            return Optional.of(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Integer> findRoleIdByName(String roleName) {
        String queryString = "SELECT * from roles where name=?";

        try {
            Integer roleId = jdbcTemplate.queryForObject(
                    queryString,
                    (rs, i) -> rs.getInt("id"),
                    roleName
            );
            return Optional.of(roleId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    private Optional<UserData> findUserDataByUsername(String username) {
        String queryString = "SELECT * FROM users where name=?";
        try {
            UserData userData = jdbcTemplate.queryForObject(
                    queryString,
                    (rs, i) -> new UserData(
                            rs.getString("name"),
                            rs.getString("password"),
                            rs.getInt("role_id")
                    ),
                    username
            );
            return Optional.of(userData);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
