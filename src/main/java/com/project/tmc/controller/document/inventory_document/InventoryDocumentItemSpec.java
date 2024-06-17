package com.project.tmc.controller.document.inventory_document;

import com.project.tmc.model.document.inventory_document.InventoryDocumentItem;
import com.project.tmc.model.document.shipment_document.ShipmentDocumentItem;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class InventoryDocumentItemSpec implements Specification<InventoryDocumentItem> {
    private final Long id;

    public InventoryDocumentItemSpec(Long id) {
        this.id = id;
    }

    @Override
    public Predicate toPredicate(Root<InventoryDocumentItem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        try {
            Expression<Long> expr = root.get("inventoryDocument").get("id").as(Long.class);
            return criteriaBuilder.equal(expr, id);
        } catch (Exception e) {
            return null;
        }
    }
}
