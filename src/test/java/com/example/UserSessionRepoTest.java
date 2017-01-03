package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class UserSessionRepoTest {
    private UserSessionRepo repo;
    private FakeSessionDataMapper dataMapper;

    @Before
    public void setUp() throws Exception {
        dataMapper = new FakeSessionDataMapper();
        repo = new UserSessionRepo(dataMapper);
    }

    @Test
    public void create_returnsSessionOnSuccess() throws Exception {
        dataMapper.create_returnValue = Optional.of(new Session("token", 1));
        User user = new User(1, "adam");

        Optional<UserSession> maybeSession = repo.create(user);

        assertThat(dataMapper.create_param_user_id, is(1));
        UserSession userSession = maybeSession.get();
        assertThat(userSession.getId(), is(1));
        assertThat(userSession.getUsername(), is("adam"));
        assertThat(userSession.getToken(), is("token"));
    }

    @Test
    public void create_returnsEmptyOnFailure() throws Exception {
        dataMapper.create_returnValue = Optional.empty();
        User user = new User(1, "adam");

        Optional<UserSession> maybeSession = repo.create(user);

        assertThat(maybeSession.isPresent(), is(false));
    }
}
