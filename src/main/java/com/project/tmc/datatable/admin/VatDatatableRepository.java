package com.project.tmc.datatable.admin;

import com.project.tmc.model.product.Vat;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface VatDatatableRepository extends DataTablesRepository<Vat, Long> {
}
