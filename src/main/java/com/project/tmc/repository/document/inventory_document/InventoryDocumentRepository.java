package com.project.tmc.repository.document.inventory_document;

import com.project.tmc.model.document.inventory_document.InventoryDocument;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDocumentRepository extends CrudRepository<InventoryDocument, Long> {
    @Query(value = "SELECT nextval('tmc_inventory_document_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
