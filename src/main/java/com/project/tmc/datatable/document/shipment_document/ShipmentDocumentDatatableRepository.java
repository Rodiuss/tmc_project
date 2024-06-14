package com.project.tmc.datatable.document.shipment_document;

import com.project.tmc.model.document.shipment_document.ShipmentDocument;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ShipmentDocumentDatatableRepository extends DataTablesRepository<ShipmentDocument, Long> {
}
