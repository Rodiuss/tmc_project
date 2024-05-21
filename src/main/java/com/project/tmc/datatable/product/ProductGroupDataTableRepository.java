package com.project.tmc.datatable.product;

import com.project.tmc.model.product.ProductGroup;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ProductGroupDataTableRepository extends DataTablesRepository<ProductGroup, Long> {
}
