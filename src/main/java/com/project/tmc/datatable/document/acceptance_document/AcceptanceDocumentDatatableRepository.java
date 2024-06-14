package com.project.tmc.datatable.document.acceptance_document;

import com.project.tmc.model.document.acceptance_document.AcceptanceDocument;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface AcceptanceDocumentDatatableRepository extends DataTablesRepository<AcceptanceDocument, Long> {
}
