package com.project.tmc.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class PasswordGeneratingModule {
    public final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    /**
     * Метод генерации рандомного пароля
     * @return Пароль
     */
    private String generatePassword() {
        int len = 16;
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    /**
     * Метод кодирования пароля
     * @return Закодированный пароль
     */
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public String getNewPassword() {
        return encodePassword(generatePassword());
    }
}
