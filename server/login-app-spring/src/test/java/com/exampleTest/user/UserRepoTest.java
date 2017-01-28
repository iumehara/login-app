package com.exampleTest.user;

import com.example.login.LoginCredentials;
import com.example.user.User;
import com.example.user.UserData;
import com.example.user.UserParams;
import com.example.user.UserRepo;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
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
    public void test_all_returnsUSers_onSuccess() throws Exception {
        dataMapper.all_returnValue = Collections.singletonList(new User(1, "adam", "staff"));

        List<User> users = repo.all();

        User user = users.get(0);
        assertThat(user.getId(), equalTo(1));
        assertThat(user.getUsername(), equalTo("adam"));
        assertThat(user.getRole(), equalTo("staff"));
    }

    @Test
    public void test_findByUsername_returnsUser_onSuccess() throws Exception {
        dataMapper.findByUsername_returnValue = Optional.of(new User(1, "adam", "staff"));


        Optional<User> maybeUser = repo.findByUsername("adam");


        assertThat(dataMapper.findByUsername_param_username, is("adam"));
        User user = maybeUser.get();
        assertThat(user.getId(), equalTo(1));
        assertThat(user.getUsername(), equalTo("adam"));
    }

    @Test
    public void test_validate_returnsUser_onSuccess() throws Exception {
        dataMapper.validate_returnValue = Optional.of(new User(1, "adam", "staff"));
        LoginCredentials credentials = new LoginCredentials("adam", "secret");


        Optional<User> maybeUser = repo.validate(credentials);


        assertThat(dataMapper.validate_param_credentials, is(credentials));
        User user = maybeUser.get();
        assertThat(user.getId(), equalTo(1));
        assertThat(user.getUsername(), equalTo("adam"));
    }

    @Test
    public void test_create_returnsUser_onSuccess() throws Exception {
        dataMapper.findRoleIdByName_returnValue = Optional.of(123);
        dataMapper.create_returnValue = Optional.of(987);

        UserParams userParams = new UserParams("adam", "secret", "staff");
        Optional<User> maybeUser = repo.create(userParams);

        assertThat(dataMapper.findRoleIdByName_param_roleName, is("staff"));
        assertThat(dataMapper.create_param_userData, Is.is(new UserData(userParams, 123)));
        User user = maybeUser.get();
        assertThat(user.getId(), equalTo(987));
        assertThat(user.getUsername(), equalTo("adam"));
        assertThat(user.getRole(), equalTo("staff"));
    }
}
