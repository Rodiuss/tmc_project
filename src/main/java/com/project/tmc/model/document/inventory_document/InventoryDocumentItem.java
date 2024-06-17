package com.project.tmc.model.document.inventory_document;

import com.project.tmc.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tmc_inventory_document_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDocumentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private InventoryDocument inventoryDocument;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity_plan", nullable = false)
    private Integer quantityPlan;

    @Column(name = "quantity_fact", nullable = false)
    private Integer quantityFact;
}
