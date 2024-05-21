package com.project.tmc.controller.admin;

import com.project.tmc.datatable.admin.RoleDataTableRepository;
import com.project.tmc.model.admin.Role;
import com.project.tmc.service.admin.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/roles")
public class RoleController {
    private final RoleService roleService;
    private final RoleDataTableRepository roleDataTableRepository;

    @GetMapping
    public String index() {
        return "admin/role/index";
    }

    /**
     * Processing datatable information
     * @param input table filter information
     * @return filtered information
     */
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<Role> ajax(@Valid @RequestBody DataTablesInput input) {
        return roleDataTableRepository.findAll(input);
    }

    /**
     * Set the initial information
     * @return initial information
     */
    @GetMapping("/create")
    public ResponseEntity<Object> create() {
        return ResponseEntity.ok().build();
    }

    /**
     * Get user information by id for edit in client form
     * @param id model`s id
     * @return model`s dto
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> edit(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(roleService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
    }

    /**
     * Update the model
     * @param role model`s data
     * @return good or bad response
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@ModelAttribute Role role) {
        try {
            roleService.update(role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(role);
    }

    /**
     * Store a new model
     * @param role model`s data
     * @return good or bad response
     */
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute Role role) {
        try {
            roleService.save(role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(role);
    }

    /**
     * Delete an existing model
     * @param id model`s id for delete
     * @return good or bad response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        try {
            roleService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(id);
    }
}
