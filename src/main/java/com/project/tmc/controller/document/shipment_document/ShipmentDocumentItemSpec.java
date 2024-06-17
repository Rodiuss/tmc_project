package com.project.tmc.controller.document.shipment_document;

import com.project.tmc.model.document.acceptance_document.AcceptanceDocumentItem;
import com.project.tmc.model.document.shipment_document.ShipmentDocumentItem;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

public class ShipmentDocumentItemSpec implements Specification<ShipmentDocumentItem> {
    private final Long id;

    public ShipmentDocumentItemSpec(Long id) {
        this.id = id;
    }

    @Override
    public Predicate toPredicate(Root<ShipmentDocumentItem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        try {
            Expression<Long> expr = root.get("acceptanceDocument").get("id").as(Long.class);
            return criteriaBuilder.equal(expr, id);
        } catch (Exception e) {
            return null;
        }
    }
}
