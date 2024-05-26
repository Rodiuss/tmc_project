package com.project.tmc.datatable.admin;

import com.project.tmc.model.admin.Vat;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface VatDatatableRepository extends DataTablesRepository<Vat, String> {
}
