package com.project.tmc.datatable.admin;

import com.project.tmc.model.product.ProductType;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ProductTypeDatatableRepository extends DataTablesRepository<ProductType, Long> {
}
