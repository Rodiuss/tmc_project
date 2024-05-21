package com.project.tmc.datatable.admin;

import com.project.tmc.model.admin.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface UserDatatableRepository extends DataTablesRepository<User, Long> {}
