package com.project.tmc.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tmc_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "article")
    private String article;

    @Column(name = "code")
    private String code;

    @Column(name = "in_stock")
    private Boolean inStock;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private ProductGroup group;

    @Column(name = "purchase_price", nullable = false)
    private Double purchasePrice;

    @Column(name = "selling_price", nullable = false)
    private Double sellingPrice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private UnitOfMeasure unit;

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "program_name")
    private ProductType type;

    @Column(name = "excisable")
    private Boolean excisable;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "barcode")
    private String barcode;

    @ManyToOne()
    @JoinColumn(name = "vat_program_name", referencedColumnName = "program_name")
    private Vat vat;
}
