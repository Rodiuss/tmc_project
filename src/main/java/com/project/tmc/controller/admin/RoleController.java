package com.project.tmc.controller.admin;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.admin.RoleDataTableRepository;
import com.project.tmc.model.user.Role;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/roles")
public class RoleController extends GenericCrudControllerImpl<Role> {
    public RoleController(GenericCrudService<Role> roleService, RoleDataTableRepository dataTableRepository) {
        super(roleService, dataTableRepository);
    }

    @GetMapping
    public String index() {
        return "admin/role/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<Role> ajax(@Valid @RequestBody DataTablesInput input) {
        return super.ajax(input);
    }

    @Override
    @GetMapping("/create")
    public ResponseEntity<Object> create() {
        return super.create();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Object> edit(@PathVariable Long id) {
        return super.edit(id);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@ModelAttribute Role model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute Role model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
