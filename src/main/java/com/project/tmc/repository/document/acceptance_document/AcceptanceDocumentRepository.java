package com.project.tmc.repository.document.acceptance_document;

import com.project.tmc.model.document.acceptance_document.AcceptanceDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptanceDocumentRepository extends CrudRepository<AcceptanceDocument, Long> {
}
