package com.example.jdbc;

import com.example.role.Role;
import com.example.role.RoleDataMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;

public class JdbcRoleDataMapper implements RoleDataMapper {
    private JdbcTemplate jdbcTemplate;

    public JdbcRoleDataMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> all() {
        String queryString = "SELECT * from roles";
        try {
            return jdbcTemplate.query(
                    queryString,
                    (rs, i) -> new Role(
                            rs.getInt("id"),
                            rs.getString("name")
                    )
            );
        } catch(Exception e) {
            return Collections.emptyList();
        }
    }
}
