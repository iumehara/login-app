package com.example;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UsersControllerTest {
    @Test
    public void test_show_returnsUser() throws Exception {
        UsersController controller = new UsersController();
        MockMvc mockController = standaloneSetup(controller).build();

        mockController.perform(MockMvcRequestBuilders.get("/users/adam"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", equalTo("adam")))
                .andExpect(jsonPath("$.password", equalTo("secret")));
    }
}
