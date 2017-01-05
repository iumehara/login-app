package com.exampleTest.Jdbc;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

class JdbcTestTemplate {
    static JdbcTemplate create() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:mysql://localhost/login_app_test");
        dataSource.setUsername("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return new JdbcTemplate(dataSource);
    }
}
