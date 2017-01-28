package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("roles")
public class RolesController {

    public RolesController() {
    }

    @GetMapping()
    public List<Role> index() {
        return Collections.singletonList(new Role(1, "staff"));
    }
}
