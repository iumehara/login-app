package com.example;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class UserRepoTest {
    @Test
    public void test_find_returnsUser() throws Exception {
        UserRepo repo = new UserRepo();

        User user = repo.findByUsername("adam");

        assertThat(user.getUsername(), equalTo("adam"));
        assertThat(user.getPassword(), equalTo("secret"));
    }
}
