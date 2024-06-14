package com.project.tmc.controller.document.acceptance_document;

import com.project.tmc.model.document.acceptance_document.AcceptanceDocument;
import com.project.tmc.model.document.acceptance_document.AcceptanceDocumentItem;
import com.project.tmc.service.GenericCrudService;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

@Slf4j
public class AcceptanceDocumentItemSpec implements Specification<AcceptanceDocumentItem> {
    private final Long id;

    public AcceptanceDocumentItemSpec (Long id) {
        this.id = id;
    }

    @Override
    public Predicate toPredicate(Root<AcceptanceDocumentItem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        log.info(root.get("acceptanceDocument").get("id").as(Long.class).toString());
        try {
            Expression<Long> expr = root.get("acceptanceDocument").get("id").as(Long.class);
            log.info(root.toString());
            return criteriaBuilder.equal(expr, id);
        } catch (Exception e) {
            return null;
        }
    }
}
