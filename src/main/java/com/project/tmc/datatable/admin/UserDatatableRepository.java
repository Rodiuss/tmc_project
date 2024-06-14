package com.project.tmc.datatable.admin;

import com.project.tmc.model.user.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface UserDatatableRepository extends DataTablesRepository<User, Long> {}
