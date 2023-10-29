package com.project.tmc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * POGO класс сущности роли
 */
@Entity
@Table(name="tmc_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements Comparable<Role> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "tmc_user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Collection<User> users;

    @Override
    public int compareTo(Role otherRole) {
        return (int) (this.getId() - otherRole.getId());
    }
}
