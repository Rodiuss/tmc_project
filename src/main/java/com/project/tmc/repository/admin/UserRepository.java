package com.project.tmc.repository.admin;

import com.project.tmc.model.admin.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Managing the {@link User} entity
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
