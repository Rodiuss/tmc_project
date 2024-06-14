package com.project.tmc.datatable.contractor;

import com.project.tmc.model.contractor.ContractorType;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ContractorTypeDatatableRepository extends DataTablesRepository<ContractorType, Long> {
}
