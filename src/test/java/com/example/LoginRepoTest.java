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
    private UserSessionRepo userSessionRepo;

    @Before
    public void setUp() throws Exception {
        userRepo = mock(UserRepo.class);
        userSessionRepo = mock(UserSessionRepo.class);

        repo = new LoginRepo(userRepo, userSessionRepo);
    }

    @Test
    public void test_getUserSession_returnsUserSessionOnSuccess() throws Exception {
        User user = new User(1, "adam", "staff");
        when(userRepo.validate(any(LoginCredentials.class)))
                .thenReturn(Optional.of(user));

        when(userSessionRepo.create(any(User.class)))
                .thenReturn(Optional.of(new UserSession(
                        new Session("token", user.getId()),
                        user
                )));

        Optional<UserSession> maybeUserSession = repo.getUserSession("adam", "secret");

        UserSession userSession = maybeUserSession.get();

        assertThat(userSession.getUsername(), is("adam"));
        assertThat(userSession.getId(), is(1));
        assertThat(userSession.getToken(), is("token"));
    }

    @Test
    public void test_getUserSession_returnsEmptyOnFailedValidation() throws Exception {
        when(userRepo.validate(any(LoginCredentials.class)))
                .thenReturn(Optional.empty());

        Optional<UserSession> maybeUserSession = repo.getUserSession("adam", "secret");

        assertThat(maybeUserSession.isPresent(), is(false));
    }

    @Test
    public void test_getUserSession_returnsEmptyOnFailedTokenGeneration() throws Exception {
        when(userRepo.validate(any(LoginCredentials.class)))
                .thenReturn(Optional.of(new User(1, "adam", "staff")));

        when(userSessionRepo.create(any(User.class)))
                .thenReturn(Optional.empty());

        Optional<UserSession> maybeUserSession = repo.getUserSession("adam", "secret");

        assertThat(maybeUserSession.isPresent(), is(false));
    }
}
