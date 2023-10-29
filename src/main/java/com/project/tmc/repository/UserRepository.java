package com.project.tmc.repository;

import com.project.tmc.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Интерфейс, необходимый для управления сущностью <b>tmc_user</b>
 * (CRUD - CREATE, READ, UPDATE, DELETE)
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
