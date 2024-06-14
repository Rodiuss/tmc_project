package com.project.tmc.model.document.shipment_document;

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
@Table(name = "tmc_shipment_document")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipmentDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;

    @Column(name = "document_date", nullable = false)
    private Date documentDate;

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private Contractor contractor;

    @JsonManagedReference
    @OneToMany(mappedBy = "acceptanceDocument", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShipmentDocumentItem> items;
}
