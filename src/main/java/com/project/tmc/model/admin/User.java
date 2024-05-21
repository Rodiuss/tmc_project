package com.project.tmc.model.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * POGO класс для сущности пользователя
 */
@Entity
@Table(name="tmc_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="surname", nullable = false)
    private String surname;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="patronymic", nullable = false)
    private String patronymic;

    @Column(name="ind_tax_num", nullable = false)
    private String individualTaxpayerNumber;

    @ManyToMany
    @JoinTable (
            name = "tmc_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private List<Role> roles;
}
