package com.project.tmc.model.document.acceptance_document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.tmc.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "tmc_acceptance_document_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcceptanceDocumentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private AcceptanceDocument acceptanceDocument;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
