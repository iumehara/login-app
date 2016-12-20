package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UsersController {
    private UserRepo repo;

    UsersController(UserRepo repo) {
        this.repo = repo;
    }

    @GetMapping("{username}")
    public User show(@PathVariable String username) {
        return repo.findByUsername(username);
    }
}
