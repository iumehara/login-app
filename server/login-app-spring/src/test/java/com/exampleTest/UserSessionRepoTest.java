package com.exampleTest;

import com.example.Session;
import com.example.User;
import com.example.UserSession;
import com.example.UserSessionRepo;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class UserSessionRepoTest {
    private UserSessionRepo repo;
    private FakeSessionDataMapper sessionDataMapper;

    @Before
    public void setUp() throws Exception {
        sessionDataMapper = new FakeSessionDataMapper();
        repo = new UserSessionRepo(sessionDataMapper);
    }

    @Test
    public void create_returnsSessionOnSuccess() throws Exception {
        User user = new User(1, "adam", "staff");
        sessionDataMapper.create_returnValue = Optional.of(
                new Session("token", user.getId())
        );

        Optional<UserSession> maybeSession = repo.create(user);

        assertThat(sessionDataMapper.create_param_user_id, is(1));
        UserSession userSession = maybeSession.get();
        assertThat(userSession.getId(), is(1));
        assertThat(userSession.getUsername(), is("adam"));
        assertThat(userSession.getToken(), is("token"));
    }

    @Test
    public void create_returnsEmptyOnFailure() throws Exception {
        sessionDataMapper.create_returnValue = Optional.empty();
        User user = new User(1, "adam", "staff");

        Optional<UserSession> maybeSession = repo.create(user);

        assertThat(maybeSession.isPresent(), is(false));
    }
}
