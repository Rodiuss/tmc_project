package com.project.tmc.service;

import com.project.tmc.model.Role;
import com.project.tmc.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    public ArrayList<Role> findAll() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.addAll(Streamable.of(roleRepository.findAll()).stream().toList());
        Collections.sort(roles);
        return roles;
    }

    public void insertRole(Role role) throws Exception{
        if (!this.findByName(role.getName()).isEmpty()) {
            throw new Exception(String.format("Role with name \"%s\" already exists!", role.getName()));
        }
        roleRepository.save(role);
    }
}
