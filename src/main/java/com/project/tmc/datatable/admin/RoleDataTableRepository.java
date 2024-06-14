package com.project.tmc.datatable.admin;

import com.project.tmc.model.user.Role;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface RoleDataTableRepository extends DataTablesRepository<Role, Long> {}
