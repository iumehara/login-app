package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class UserRepoTest {
    private FakeUserDataMapper dataMapper;
    private UserRepo repo;

    @Before
    public void setUp() throws Exception {
        dataMapper = new FakeUserDataMapper();
        repo = new UserRepo(dataMapper);
    }

    @Test
    public void test_findByUsername_returnsUser_onSuccess() throws Exception {
        dataMapper.findByUsername_returnValue = Optional.of(new User(1, "adam"));


        Optional<User> maybeUser = repo.findByUsername("adam");


        assertThat(dataMapper.findByUsername_param_username, is("adam"));
        User user = maybeUser.get();
        assertThat(user.getId(), equalTo(1));
        assertThat(user.getUsername(), equalTo("adam"));
    }

    @Test
    public void test_validate_returnsUser_onSuccess() throws Exception {
        dataMapper.validate_returnValue = Optional.of(new User(1, "adam"));
        LoginCredentials credentials = new LoginCredentials("adam", "secret");


        Optional<User> maybeUser = repo.validate(credentials);


        assertThat(dataMapper.validate_param_credentials, is(credentials));
        User user = maybeUser.get();
        assertThat(user.getId(), equalTo(1));
        assertThat(user.getUsername(), equalTo("adam"));
    }

    @Test
    public void test_create_returnsUser_onSuccess() throws Exception {
        UserParams userParams = new UserParams("adam");
        dataMapper.create_returnValue = Optional.of(new User(1, "adam"));


        Optional<User> maybeUser = repo.create(userParams);


        assertThat(dataMapper.create_param_userParams, is(userParams));
        User user = maybeUser.get();
        assertNotNull(user.getId());
        assertThat(user.getUsername(), equalTo("adam"));
    }
}
