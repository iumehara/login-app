package com.exampleTest.login;

import com.example.login.TokenGenerator;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class TokenGeneratorTest {

    @Test
    public void test_createRandom_returnsToken() throws Exception {
        String token = TokenGenerator.createRandom();
        assertThat(token.length(), is(26));
    }
}
