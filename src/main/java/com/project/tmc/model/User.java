package com.project.tmc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Comparator;

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
public class User implements Comparable<User> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="username", nullable = false)
    private String username;

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
    private String IndividualTaxpayerNumber;

    @ManyToMany
    @JoinTable(
            name = "tmc_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private Collection<Role> roles;

    @Override
    public int compareTo(User otherUser) {
        return (int) (this.getId() - otherUser.getId());
    }

    public boolean hasRole(Role role) {
        if (roles == null) {
            return false;
        }
        return roles.contains(role);
    }
}
