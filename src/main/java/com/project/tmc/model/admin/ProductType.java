package com.project.tmc.model.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tmc_product_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductType {
    @Id
    @Column(name = "program_name")
    private String programName;

    @Column(name = "name")
    private String name;
}
