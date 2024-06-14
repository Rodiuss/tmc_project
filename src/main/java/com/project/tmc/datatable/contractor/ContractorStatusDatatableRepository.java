package com.project.tmc.datatable.contractor;

import com.project.tmc.model.contractor.ContractorStatus;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ContractorStatusDatatableRepository extends DataTablesRepository<ContractorStatus, Long> {
}
