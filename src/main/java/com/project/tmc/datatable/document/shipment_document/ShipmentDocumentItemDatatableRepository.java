package com.project.tmc.datatable.document.shipment_document;

import com.project.tmc.model.document.shipment_document.ShipmentDocumentItem;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ShipmentDocumentItemDatatableRepository extends DataTablesRepository<ShipmentDocumentItem, Long> {
}
