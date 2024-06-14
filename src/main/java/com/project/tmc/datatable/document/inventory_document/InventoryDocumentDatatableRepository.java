package com.project.tmc.datatable.document.inventory_document;

import com.project.tmc.model.document.inventory_document.InventoryDocument;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface InventoryDocumentDatatableRepository extends DataTablesRepository<InventoryDocument, Long> {
}
