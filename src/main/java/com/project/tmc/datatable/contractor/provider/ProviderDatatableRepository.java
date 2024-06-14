package com.project.tmc.datatable.contractor.provider;

import com.project.tmc.model.contractor.Contractor;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ProviderDatatableRepository extends DataTablesRepository<Contractor, Long> {
}
