package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class LoginRepoTest {
    private LoginRepo repo;
    private UserRepo userRepo;
    private SessionRepo sessionRepo;

    @Before
    public void setUp() throws Exception {
        userRepo = mock(UserRepo.class);
        sessionRepo = mock(SessionRepo.class);

        repo = new LoginRepo(userRepo, sessionRepo);
    }

    @Test
    public void test_getUserSession_returnsUserSessionOnSuccess() throws Exception {
        User user = new User(1, "adam");
        when(userRepo.validate(any(LoginCredentials.class)))
                .thenReturn(Optional.of(user));

        when(sessionRepo.create(any(User.class)))
                .thenReturn(Optional.of(new Session("token", user)));

        Optional<Session> maybeUserSession = repo.getUserSession("adam", "secret");

        Session session = maybeUserSession.get();

        assertThat(session.getUsername(), is("adam"));
        assertThat(session.getId(), is(1));
        assertThat(session.getToken(), is("token"));
    }

    @Test
    public void test_getUserSession_returnsEmptyOnFailedValidation() throws Exception {
        when(userRepo.validate(any(LoginCredentials.class)))
                .thenReturn(Optional.empty());

        Optional<Session> maybeUserSession = repo.getUserSession("adam", "secret");

        assertThat(maybeUserSession.isPresent(), is(false));
    }

    @Test
    public void test_getUserSession_returnsEmptyOnFailedTokenGeneration() throws Exception {
        when(userRepo.validate(any(LoginCredentials.class)))
                .thenReturn(Optional.of(new User(1, "adam")));

        when(sessionRepo.create(any(User.class)))
                .thenReturn(Optional.empty());

        Optional<Session> maybeUserSession = repo.getUserSession("adam", "secret");

        assertThat(maybeUserSession.isPresent(), is(false));
    }
}
