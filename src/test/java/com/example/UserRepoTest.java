package com.example;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class UserRepoTest {
    @Test
    public void test_find_returnsUser() throws Exception {
        FakeUserDataMapper dataMapper = new FakeUserDataMapper();
        dataMapper.findByUsername_returnValue = Optional.of(new User("adam"));
        UserRepo repo = new UserRepo(dataMapper);


        Optional<User> maybeUser = repo.findByUsername("adam");


        User user = maybeUser.get();
        assertThat(user.getUsername(), equalTo("adam"));
    }
}
