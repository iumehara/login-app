package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class SessionRepoTest {
    private SessionRepo repo;
    private FakeSessionDataMapper dataMapper;

    @Before
    public void setUp() throws Exception {
        dataMapper = new FakeSessionDataMapper();
        repo = new SessionRepo(dataMapper);
    }

    @Test
    public void create_returnsSessionOnSuccess() throws Exception {
        dataMapper.create_returnValue = Optional.of(new Session("token", new User(1, "adam")));
        User user = new User(1, "adam");

        Optional<Session> maybeSession = repo.create(user);

        assertThat(dataMapper.create_param_user_id, is(1));
        Session session = maybeSession.get();
        assertThat(session.getId(), is(1));
        assertThat(session.getUsername(), is("adam"));
        assertThat(session.getToken(), is("token"));
    }

    @Test
    public void create_returnsEmptyOnFailure() throws Exception {
        dataMapper.create_returnValue = Optional.empty();
        User user = new User(1, "adam");

        Optional<Session> maybeSession = repo.create(user);

        assertThat(maybeSession.isPresent(), is(false));
    }
}
