package com.example.jdbc;

import com.example.role.Role;
import com.example.role.RoleDataMapper;

import java.util.Collections;
import java.util.List;

public class JdbcRoleDataMapper implements RoleDataMapper {

    @Override
    public List<Role> all() {
        return Collections.singletonList(
                new Role(1, "staff")
        );
    }
}
