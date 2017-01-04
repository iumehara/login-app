package com.example.Jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

class JdbcTestTemplate {
    static JdbcTemplate create() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://localhost/login_app_test");
        dataSource.setUser("root");
        return new JdbcTemplate(dataSource);
    }
}
