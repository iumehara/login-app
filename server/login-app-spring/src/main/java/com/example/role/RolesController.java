package com.example.role;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RolesController {
    private RoleRepo repo;

    public RolesController(RoleRepo repo) {
        this.repo = repo;
    }

    @GetMapping()
    public List<Role> index() {
        return repo.all();
    }
}
