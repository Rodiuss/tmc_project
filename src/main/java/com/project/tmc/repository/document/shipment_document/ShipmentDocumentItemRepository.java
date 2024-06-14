package com.project.tmc.repository.document.shipment_document;

import com.project.tmc.model.document.shipment_document.ShipmentDocumentItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentDocumentItemRepository extends CrudRepository<ShipmentDocumentItem, Long> {
}
