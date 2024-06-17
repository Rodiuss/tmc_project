package com.project.tmc.repository.document.acceptance_document;

import com.project.tmc.model.document.acceptance_document.AcceptanceDocument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptanceDocumentRepository extends CrudRepository<AcceptanceDocument, Long> {
    @Query(value = "SELECT nextval('tmc_acceptance_document_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
