package com.project.tmc.repository;

import com.project.tmc.model.Role;
import com.project.tmc.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Интерфейс, необходимый для управления сущностью <b>tmc_role</b>
 * (CRUD - CREATE, READ, UPDATE, DELETE)
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
