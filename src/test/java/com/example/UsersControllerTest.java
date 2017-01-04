package com.example;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UsersControllerTest {
    private UserRepo mockRepo;
    private MockMvc mockController;

    @Before
    public void setUp() throws Exception {
        mockRepo = mock(UserRepo.class);
        UsersController controller = new UsersController(mockRepo);
        mockController = standaloneSetup(controller).build();
    }

    @Test
    public void test_show_returnsUser() throws Exception {
        when(mockRepo.findByUsername("adam"))
                .thenReturn(Optional.of(new User(1, "adam", "staff")));

        mockController.perform(get("/users/adam"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.username", equalTo("adam")));
    }

    @Test
    public void test_create_returnsCreatedUser_onSuccess() throws Exception {
        when(mockRepo.create(new UserParams("adam", "secret")))
            .thenReturn(Optional.of(new User(1, "adam", "staff")));

        String userPayload = "{\"username\":\"adam\",\"password\":\"secret\"}";

        mockController.perform(
                post("/users")
                        .contentType(APPLICATION_JSON_UTF8_VALUE)
                        .content(userPayload)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is("adam")))
                .andExpect(jsonPath("$.id").exists());
    }
}
