package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {
    private UserRepo repo;

    public UsersController(UserRepo repo) {
        this.repo = repo;
    }

    @GetMapping("")
    public List<User> index() {
        return repo.all();
    }

    @GetMapping("{username}")
    public User show(@PathVariable String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("error"));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody UserParams userParams) {
        return repo.create(userParams)
                .orElseThrow(() -> new RuntimeException("error"));
    }
}
