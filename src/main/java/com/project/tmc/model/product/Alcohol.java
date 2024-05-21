package com.project.tmc.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tmc_alcohol")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alcohol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id", unique = true, nullable = false, referencedColumnName = "id")
    private Product product;

    @Column(name = "volume")
    private Double volume;

    @Column(name = "alcohol_barcode")
    private String alcoholBarcode;

    @Column(name = "strength")
    private Double strength;

    @Column(name = "alcohol_type_code")
    private String alcoholTypeCode;
}
