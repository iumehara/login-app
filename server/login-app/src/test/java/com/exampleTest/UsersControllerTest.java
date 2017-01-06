package com.exampleTest;

import com.example.User;
import com.example.UserParams;
import com.example.UserRepo;
import com.example.UsersController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
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
    public void test_index_returnsUsers() throws Exception {
        when(mockRepo.all())
                .thenReturn(Collections.singletonList(new User(1, "adam", "staff")));

        mockController.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].username", equalTo("adam")))
                .andExpect(jsonPath("$[0].role", equalTo("staff")));
    }

    @Test
    public void test_show_returnsUser() throws Exception {
        when(mockRepo.findByUsername("adam"))
                .thenReturn(Optional.of(new User(1, "adam", "staff")));

        mockController.perform(get("/users/adam"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.username", equalTo("adam")))
                .andExpect(jsonPath("$.role", equalTo("staff")));
    }

    @Test
    public void test_create_returnsCreatedUser_onSuccess() throws Exception {
        when(mockRepo.create(new UserParams("adam", "secret", "staff")))
            .thenReturn(Optional.of(new User(1, "adam", "staff")));

        String userPayload = "{\"username\":\"adam\",\"password\":\"secret\",\"role\":\"staff\"}";
        mockController.perform(
                post("/users")
                        .contentType(APPLICATION_JSON_UTF8_VALUE)
                        .content(userPayload)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username", is("adam")))
                .andExpect(jsonPath("$.role", is("staff")));
    }
}
