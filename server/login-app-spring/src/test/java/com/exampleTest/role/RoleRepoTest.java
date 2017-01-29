package com.exampleTest.role;

import com.example.jdbc.JdbcRoleDataMapper;
import com.example.role.Role;
import com.example.role.RoleRepo;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleRepoTest {
    @Test
    public void test_all_returnsAllRoles() throws Exception {
        JdbcRoleDataMapper dataMapper = mock(JdbcRoleDataMapper.class);
        when(dataMapper.all()).thenReturn(Collections.singletonList(
                new Role(1, "staff")
        ));

        RoleRepo repo = new RoleRepo(dataMapper);

        List<Role> roles = repo.all();

        Role role = roles.get(0);
        assertThat(role.getId(), equalTo(1));
        assertThat(role.getName(), equalTo("staff"));
    }
}
