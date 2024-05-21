package com.project.tmc.datatable.product;

import com.project.tmc.model.product.Product;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ProductDataTableRepository extends DataTablesRepository<Product, Long> {
}
