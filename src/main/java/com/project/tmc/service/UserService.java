package com.project.tmc.service;

import com.project.tmc.model.User;
import com.project.tmc.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

/**
 * Класс, содержащий логику работы с пользователями
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    final UserRepository userRepository;
    final RoleService roleService;

    final PasswordService passwordService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь %s не найден", username)
        ));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList()
        );
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(Streamable.of(userRepository.findAll()).toList());
        Collections.sort(users);

        return users;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Метод для добавления нового пользователя
     * @param user - информация о email, username и role
     */
    public void insertUser(User user) throws Exception {
        if (!this.findByUsername(user.getUsername()).isEmpty()) {
            throw new Exception(String.format("User with username \"%s\" is already exist!", user.getUsername()));
        } else if (!userRepository.findByEmail(user.getEmail()).isEmpty()) {
            throw new Exception(String.format("User with email \"%s\" is already exist!", user.getEmail()));
        }
        try {
            user.setPassword(passwordService.getNewPassword());
            userRepository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    /**
     * Метод для удаления пользователя
     * @param userId - информация о email, username и role
     */
    public void deleteUser(Long userId) throws Exception {
        if (userRepository.findById(userId).isEmpty()) {
            throw new Exception(String.format("User with id \"%d\" does not exist!", userId));
        }

        userRepository.delete(userRepository.findById(userId).get());
    }

    /**
     * Метод для обновления пользователя
     * @param user - информация о email, username и role
     */
    public void updateUser(User user) throws Exception{
        if (!userRepository.existsById(user.getId())) {
            throw new Exception("User with id %d does not exists!");
        }
        user.setPassword(findById(user.getId()).orElseThrow().getPassword());
        userRepository.save(user);
    }
}
