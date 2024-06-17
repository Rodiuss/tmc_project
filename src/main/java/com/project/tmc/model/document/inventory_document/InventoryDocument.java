package com.project.tmc.model.document.inventory_document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.tmc.model.contractor.Contractor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "tmc_inventory_document")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;

    @Column(name = "document_date", nullable = false)
    private Date documentDate;

    @JsonBackReference
    @OneToMany(mappedBy = "inventoryDocument", cascade = CascadeType.ALL)
    private List<InventoryDocumentItem> items;
}
