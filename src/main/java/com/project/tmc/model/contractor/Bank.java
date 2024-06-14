package com.project.tmc.model.contractor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tmc_bank")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    // БИК (уникальный банковский номер, принадлежащий конкретному отделению кредитной организации)
    @Column(name = "bic", unique = true, nullable = false)
    private String bic;

    // Корреспондентский счет (счет, открываемый банковской организацией в подразделении самого банка)
    @Column(name = "correspondent_account", unique = true, nullable = false)
    private String correspondentAccount;

    @JsonIgnore
    @OneToMany(mappedBy = "bank", cascade = CascadeType.REMOVE)
    private List<Contractor> contractorList;
}
