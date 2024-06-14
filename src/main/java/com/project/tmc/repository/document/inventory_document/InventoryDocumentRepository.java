package com.project.tmc.repository.document.inventory_document;

import com.project.tmc.model.document.inventory_document.InventoryDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryDocumentRepository extends CrudRepository<InventoryDocument, Long> {
}
