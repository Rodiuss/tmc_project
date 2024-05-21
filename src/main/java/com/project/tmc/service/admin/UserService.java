package com.project.tmc.service.admin;

import com.project.tmc.dto.UserDto;
import com.project.tmc.model.admin.User;
import com.project.tmc.repository.admin.UserRepository;
import com.project.tmc.module.PasswordGeneratingModule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, содержащий логику работы с пользователями
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    final RoleService roleService;
    final UserRepository userRepository;
    final PasswordGeneratingModule passwordGeneratingModule;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                "Пользователь не найден."
        ));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(String.format("ROLE_%s", role.getFrameworkName()))).toList()
        );
    }

    public UserDto getDto(User user) {
        List<Long> roles_list = new ArrayList<>();
        user.getRoles().forEach((item) -> roles_list.add(item.getId()));

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .patronymic(user.getPatronymic())
                .individualTaxpayerNumber(user.getIndividualTaxpayerNumber())
                .roles(roles_list)
                .build();
    }

    private User getUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("Пользователь не найден."));
    }

    public UserDto getDtoById(Long id) throws Exception {
        return getDto(getUserById(id));
    }

    public void delete(Long id) throws Exception {
        getUserById(id);
        userRepository.deleteById(id);
    }

    public void save(User user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Пользователь с такой почтой уже существует.");
        }

        if (user.getPassword().isEmpty()) {
            user.setPassword(passwordGeneratingModule.getNewPassword());
        }

        userRepository.save(user);
    }

    @SneakyThrows
    public void saveAll(List<User> users){
        userRepository.saveAll(users);
    }

    public void update(User user) throws Exception {
        getUserById(user.getId());

        user.setPassword(user.getPassword().isEmpty()
                ? getUserById(user.getId()).getPassword()
                : passwordGeneratingModule.encodePassword(user.getPassword()));

        userRepository.save(user);
    }
}
