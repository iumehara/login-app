package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class JdbcUserDataMapperTest {
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = JdbcTestTemplate.create();

        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName("users")
                .usingColumns("name", "password")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("name", "adam");
        params.put("password", "secret");

        insert.execute(params);
    }

    @After
    public void tearDown() throws Exception {
        jdbcTemplate.update("DELETE FROM users");
    }

    @Test
    public void test_findByUsername_returnsUserOnSuccess() throws Exception {
        JdbcUserDataMapper dataMapper = new JdbcUserDataMapper(jdbcTemplate);
        Optional<User> maybeUser = dataMapper.findByUsername("adam");

        User user = maybeUser.get();

        assertThat(user.getUsername(), is("adam"));
        assertNotNull(user.getId());
    }

    @Test
    public void test_findByUsername_returnsEmptyOnFailure() throws Exception {
        JdbcUserDataMapper dataMapper = new JdbcUserDataMapper(jdbcTemplate);

        Optional<User> maybeUser = dataMapper.findByUsername("bob");

        assertThat(maybeUser.isPresent(), is(false));
    }

    @Test
    public void test_validate_returnsUserOnSuccess() throws Exception {
        JdbcUserDataMapper dataMapper = new JdbcUserDataMapper(jdbcTemplate);

        Optional<User> maybeUser = dataMapper.validate(
                new LoginCredentials("adam", "secret")
        );

        User user = maybeUser.get();

        assertThat(user.getUsername(), is("adam"));
        assertNotNull(user.getId());
    }

    @Test
    public void test_validate_returnsEmptyOnFailure() throws Exception {
        JdbcUserDataMapper dataMapper = new JdbcUserDataMapper(jdbcTemplate);

        Optional<User> maybeUser = dataMapper.validate(
                new LoginCredentials("adam", "wrongPassword")
        );

        assertThat(maybeUser.isPresent(), is(false));
    }
}
