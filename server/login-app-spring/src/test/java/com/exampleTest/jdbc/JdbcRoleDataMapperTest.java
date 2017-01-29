package com.exampleTest.jdbc;

import com.example.jdbc.JdbcRoleDataMapper;
import com.example.role.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class JdbcRoleDataMapperTest {
    private JdbcTestTemplate jdbcTestTemplate;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        jdbcTestTemplate = new JdbcTestTemplate();

        jdbcTestTemplate.insertRoleIntoDatabase("staff");
        jdbcTestTemplate.insertRoleIntoDatabase("admin");
        this.jdbcTemplate = jdbcTestTemplate.jdbcTemplate;
    }

    @After
    public void tearDown() throws Exception {
        jdbcTestTemplate.cleanDatabase();
    }

    @Test
    public void test_all_returnsRoles_onSuccess() throws Exception {
        JdbcRoleDataMapper dataMapper = new JdbcRoleDataMapper(jdbcTemplate);

        List<Role> roles = dataMapper.all();

        assertThat(roles.size(), equalTo(2));
        assertThat(roles.get(0).getId(), equalTo(1));
        assertThat(roles.get(0).getName(), equalTo("staff"));
        assertThat(roles.get(1).getId(), equalTo(2));
        assertThat(roles.get(1).getName(), equalTo("admin"));
    }
}
