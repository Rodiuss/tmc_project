package com.project.tmc.datatable.document.inventory_document;

import com.project.tmc.model.document.inventory_document.InventoryDocumentItem;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface InventoryDocumentItemDatatableRepository extends DataTablesRepository<InventoryDocumentItem, Long> {
}
