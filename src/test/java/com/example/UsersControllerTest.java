package com.example;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UsersControllerTest {
    @Test
    public void test_show_returnsUser() throws Exception {
        UserRepo mockRepo = mock(UserRepo.class);
        UsersController controller = new UsersController(mockRepo);
        MockMvc mockController = standaloneSetup(controller).build();

        when(mockRepo.findByUsername("adam"))
                .thenReturn(Optional.of(new User("adam", "secret")));

        mockController.perform(MockMvcRequestBuilders.get("/users/adam"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", equalTo("adam")))
                .andExpect(jsonPath("$.password", equalTo("secret")));
    }
}
