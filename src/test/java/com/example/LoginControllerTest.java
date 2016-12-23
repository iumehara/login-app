package com.example;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class LoginControllerTest {

    private LoginRepo repo;
    private MockMvc mockController;

    @Before
    public void setUp() throws Exception {
        repo = mock(LoginRepo.class);
        LoginController controller = new LoginController(repo);
        mockController = standaloneSetup(controller).build();
    }

    @Test
    public void test_create_returnsUserSessionOnSuccess() throws Exception {
        when(repo.getUserSession("adam", "secreta"))
                .thenReturn(Optional.of(new Session(
                        "XXXFAKETOKENXXX",
                        new User(1, "adam")
                )));

        mockController.perform(post("/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"username\":\"adam\",\"password\":\"secreta\"}")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", equalTo("XXXFAKETOKENXXX")))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.username", equalTo("adam")));
    }
}
