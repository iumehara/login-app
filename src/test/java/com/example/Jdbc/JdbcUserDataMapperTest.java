package com.example.Jdbc;

import com.example.LoginCredentials;
import com.example.User;
import com.example.UserParams;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class JdbcUserDataMapperTest {
    private JdbcTemplate jdbcTemplate;
    private JdbcUserDataMapper dataMapper;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = JdbcTestTemplate.create();

        insertUserIntoDatabase();
        insertRoleIntoDatabase("staff");
        insertRoleIntoDatabase("admin");

        dataMapper = new JdbcUserDataMapper(jdbcTemplate);
    }

    private void insertUserIntoDatabase() {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName("users")
                .usingColumns("name", "password", "role_id")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("name", "adam");
        params.put("password", "secret");
        params.put("role_id", 1);

        insert.execute(params);
    }

    private void insertRoleIntoDatabase(String role) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName("roles")
                .usingColumns("name")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("name", role);

        insert.execute(params);
    }

    @After
    public void tearDown() throws Exception {
        jdbcTemplate.update("DELETE FROM users");
    }

    @Test
    public void test_findByUsername_returnsUserOnSuccess() throws Exception {
        Optional<User> maybeUser = dataMapper.findByUsername("adam");

        User user = maybeUser.get();

        assertNotNull(user.getId());
        assertThat(user.getUsername(), is("adam"));
        assertThat(user.getRole(), is("staff"));
    }

    @Test
    public void test_findByUsername_returnsEmptyOnFailure() throws Exception {
        Optional<User> maybeUser = dataMapper.findByUsername("bob");

        assertThat(maybeUser.isPresent(), is(false));
    }

    @Test
    public void test_validate_returnsUserOnSuccess() throws Exception {
        Optional<User> maybeUser = dataMapper.validate(
                new LoginCredentials("adam", "secret")
        );

        User user = maybeUser.get();

        assertNotNull(user.getId());
        assertThat(user.getUsername(), is("adam"));
        assertThat(user.getRole(), is("staff"));
    }

    @Test
    public void test_validate_returnsEmptyOnFailure() throws Exception {
        Optional<User> maybeUser = dataMapper.validate(
                new LoginCredentials("adam", "wrongPassword")
        );

        assertThat(maybeUser.isPresent(), is(false));
    }

    @Test
    public void test_create_returnsUserOnSuccess() throws Exception {
        UserParams userParams = new UserParams("adam", "secret");

        Optional<User> maybeUser = dataMapper.create(userParams);

        User user = maybeUser.get();
        assertThat(user.getUsername(), is("adam"));
        assertNotNull(user.getId());
    }

    @Test
    public void test_create_returnsEmptyOnFailure() throws Exception {
        UserParams userParams = new UserParams("adam", null);

        Optional<User> maybeUser = dataMapper.create(userParams);

        assertFalse(maybeUser.isPresent());
    }
}
