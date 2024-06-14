package com.project.tmc.datatable.document.acceptance_document;

import com.project.tmc.model.document.acceptance_document.AcceptanceDocumentItem;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface AcceptanceDocumentItemDatatableRepository extends DataTablesRepository<AcceptanceDocumentItem, Long> {
}
