package com.project.tmc.repository.admin;

import com.project.tmc.model.user.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
    Optional<Role> findByFrameworkName(String name);
}
