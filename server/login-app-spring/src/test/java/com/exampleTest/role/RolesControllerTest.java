package com.exampleTest.role;

import com.example.role.Role;
import com.example.role.RoleRepo;
import com.example.role.RolesController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class RolesControllerTest {

    @Test
    public void test_index_returnsRoles_onSuccess() throws Exception {
        RoleRepo repo = mock(RoleRepo.class);
        when(repo.all()).thenReturn(Collections.singletonList(
                new Role(1, "staff")
        ));

        RolesController rolesController = new RolesController(repo);
        MockMvc mockController = standaloneSetup(rolesController).build();

        mockController.perform(MockMvcRequestBuilders.get("/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("staff")));
    }
}
