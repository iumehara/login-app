package com.exampleTest.jdbc;

import com.example.security.Encoder;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;

class JdbcTestTemplate {
    JdbcTemplate jdbcTemplate;

    JdbcTestTemplate() {
        this.jdbcTemplate = create();
    }

    private JdbcTemplate create() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:mysql://localhost/login_app_test");
        dataSource.setUsername("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return new JdbcTemplate(dataSource);
    }

    void insertUserIntoDatabase() {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName("users")
                .usingColumns("name", "password", "role_id")
                .usingGeneratedKeyColumns("id");

        String encodedPassword = new Encoder().encode("secret");
        Map<String, Object> params = new HashMap<>();
        params.put("name", "adam");
        params.put("password", encodedPassword);
        params.put("role_id", 1);

        insert.execute(params);
    }

    void insertRoleIntoDatabase(String role) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(this.jdbcTemplate)
                .withTableName("roles")
                .usingColumns("name")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("name", role);

        insert.execute(params);
    }

    void cleanDatabase() {
        deleteUsers();
        deleteRoles();
    }

    private void deleteUsers() {
        jdbcTemplate.update("DELETE FROM users");
    }

    private void deleteRoles() {
        jdbcTemplate.update("DELETE FROM roles");
        jdbcTemplate.update("ALTER table roles AUTO_INCREMENT=1");
    }
}
