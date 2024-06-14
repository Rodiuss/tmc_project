package com.project.tmc.datatable.contractor;

import com.project.tmc.model.contractor.Bank;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface BankDataTablesRepository extends DataTablesRepository<Bank, Long> {
}
