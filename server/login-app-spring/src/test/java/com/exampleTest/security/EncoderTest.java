package com.exampleTest.security;

import com.example.security.Encoder;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class EncoderTest {
    @Test
    public void test_encode() throws Exception {
        Encoder encoder = new Encoder();
        String rawPassword = "topSecret";


        String encodedPassword = encoder.encode(rawPassword);


        assertNotEquals(encodedPassword, "topSecret");
        assertThat(encodedPassword.length(), equalTo(60));
    }

    @Test
    public void test_matches_successfulWithCorrectHardCodedValue() throws Exception {
        String encodedPasswordSample = "$2a$10$K5ibLGXDRpmBwQU9a88QSOfIZANYqfW5iImidhTb45fXx9UuIn6La";


        boolean matchFound = new Encoder().matches("topSecret", encodedPasswordSample);


        assertTrue(matchFound);
    }

    @Test
    public void test_matches_failsWithWrongHardCodedValue() throws Exception {
        String encodedPasswordSample = "$2a$10$K5ibLGXDRpmBwQU9a88QSOfIZANYqfW5iImidhTb45fXx9UuIn6La";


        boolean matchFound = new Encoder().matches("wrongSecret", encodedPasswordSample);


        assertFalse(matchFound);
    }

    @Test
    public void test_encodeAndMatches_worksTogether() throws Exception {
        Encoder encoder = new Encoder();
        String rawPassword = "topSecret";


        String encodedPassword = encoder.encode(rawPassword);
        boolean matchFound = encoder.matches("topSecret", encodedPassword);


        assertTrue(matchFound);
    }
}
