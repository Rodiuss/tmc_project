package com.project.tmc.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tmc_product_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_group_id", referencedColumnName = "id")
    private ProductGroup parentGroup;

    @OneToMany(mappedBy = "parentGroup")
    private List<ProductGroup> childrenGroups;
}
