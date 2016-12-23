package com.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
    private LoginRepo repo;

    LoginController(LoginRepo repo) {
        this.repo = repo;
    }

    @PostMapping
    public UserSession create(@RequestBody LoginCredentials credentials) {
        return repo.getUserSession(credentials.getUsername(), credentials.getPassword())
                .orElseThrow(() -> new RuntimeException("error"));
    }
}

