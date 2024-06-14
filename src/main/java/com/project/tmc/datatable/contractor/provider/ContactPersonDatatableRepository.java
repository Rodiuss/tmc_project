package com.project.tmc.datatable.contractor.provider;

import com.project.tmc.model.contractor.ContactPerson;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface ContactPersonDatatableRepository extends DataTablesRepository<ContactPerson, Long> {
}
