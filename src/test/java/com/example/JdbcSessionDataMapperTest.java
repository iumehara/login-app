package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class JdbcSessionDataMapperTest {
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = JdbcTestTemplate.create();
    }

    @After
    public void tearDown() throws Exception {
        jdbcTemplate.update("DELETE FROM sessions");
    }

    @Test
    public void test_create_returnsSessionOnSuccess() throws Exception {
        JdbcSessionDataMapper dataMapper = new JdbcSessionDataMapper(jdbcTemplate);

        Optional<Session> maybeSession = dataMapper.create(2, "secondToken");
        System.out.println("maybeSession = " + maybeSession);
        assertThat(maybeSession.get().getToken(), is("secondToken"));
    }
}
