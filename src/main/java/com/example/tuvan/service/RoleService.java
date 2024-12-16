package com.example.tuvan.service;


import com.example.tuvan.entity.Role;
import com.example.tuvan.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
}
