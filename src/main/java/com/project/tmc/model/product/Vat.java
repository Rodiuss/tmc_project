package com.project.tmc.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tmc_vat")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "program_name", nullable = false, unique = true)
    private String programName;

    @Column(name = "name")
    private String name;
}
