package com.example.role;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepo {
    private RoleDataMapper dataMapper;

    public RoleRepo(RoleDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public List<Role> all() {
        return dataMapper.all();
    }
}
