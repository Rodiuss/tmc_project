package com.project.tmc.repository.admin;

import com.project.tmc.model.admin.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Managing the {@link Role} entity
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
    Optional<Role> findByFrameworkName(String name);
}
