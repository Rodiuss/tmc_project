package com.project.tmc.repository.document.shipment_document;

import com.project.tmc.model.document.shipment_document.ShipmentDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentDocumentRepository extends CrudRepository<ShipmentDocument, Long> {
}
