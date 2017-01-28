package com.exampleTest.jdbc;

import com.example.jdbc.JdbcUserDataMapper;
import com.example.login.LoginCredentials;
import com.example.user.User;
import com.example.user.UserData;
import com.example.user.UserParams;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
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
        jdbcTemplate.update("DELETE FROM roles");
        jdbcTemplate.update("ALTER table roles AUTO_INCREMENT=1");
    }

    @Test
    public void test_all_returnsUsersOnSuccess() throws Exception {
        List<User> users = dataMapper.all();

        User user = users.get(0);
        assertNotNull(user.getId());
        assertThat(user.getUsername(), is("adam"));
        assertThat(user.getRole(), is("staff"));
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
        UserData userData = new UserData(
                new UserParams("adam", "secret", "staff"),
                1
        );

        Optional<Integer> maybeInteger = dataMapper.create(userData);

        assertNotNull(maybeInteger.get());
    }

    @Test
    public void test_create_returnsEmptyOnFailure() throws Exception {
        UserData userData = new UserData(
                new UserParams("adam", null, "staff"),
                1
        );

        Optional<Integer> maybeInteger = dataMapper.create(userData);

        assertFalse(maybeInteger.isPresent());
    }

    @Test
    public void test_findRoleIdByName_returnsIntegerOnSuccess() throws Exception {
        Optional<Integer> maybeRoleId = dataMapper.findRoleIdByName("staff");

        assertNotNull(maybeRoleId.get());
    }

    @Test
    public void test_findRoleIdByName_returnsEmptyOnFailure() throws Exception {
        Optional<Integer> maybeRoleId = dataMapper.findRoleIdByName(null);

        assertFalse(maybeRoleId.isPresent());
    }
}
