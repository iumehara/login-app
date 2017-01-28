package com.exampleTest.role;

import com.example.role.RolesController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class RolesControllerTest {
    MockMvc mockController;

    @Test
    public void test_index_returnsRoles_onSuccess() throws Exception {
        RolesController rolesController = new RolesController();
        mockController = standaloneSetup(rolesController).build();

        mockController.perform(MockMvcRequestBuilders.get("/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("staff")));
    }
}
