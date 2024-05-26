package com.project.tmc.datatable.admin;

import com.project.tmc.model.admin.ProductType;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ProductTypeDatatableRepository extends DataTablesRepository<ProductType, String> {
}
