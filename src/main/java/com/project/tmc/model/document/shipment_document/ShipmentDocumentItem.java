package com.project.tmc.model.document.shipment_document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.tmc.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tmc_shipment_document_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentDocumentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private ShipmentDocument shipmentDocument;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
