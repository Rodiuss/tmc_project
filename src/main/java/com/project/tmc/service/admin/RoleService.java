package com.project.tmc.service.admin;

import com.project.tmc.model.admin.Role;
import com.project.tmc.repository.admin.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    public boolean isEmpty() {
        return !roleRepository.findAll().iterator().hasNext();
    }

    public Role getById(Long id) throws Exception {
        return roleRepository.findById(id).orElseThrow(() -> (new Exception("Роль не найдена")));
    }

    public void delete(Long id) throws Exception {
        getById(id);
        try {
            roleRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Роль используется");
        }
    }

    public void save(Role role) throws Exception {
        if (roleRepository.findByName(role.getFrameworkName()).isPresent()) {
            throw new Exception("Роль с таким программным наименованием уже существует.");
        } else if (roleRepository.findByFrameworkName(role.getFrameworkName()).isPresent()) {
            throw new Exception("Роль с таким наименованием уже существует.");
        }

        roleRepository.save(role);
    }

    @SneakyThrows
    public void saveAll(List<Role> roles) {
        roleRepository.saveAll(roles);
    }

    public void update(Role role) throws Exception {
        getById(role.getId());
        roleRepository.save(role);
    }
}

