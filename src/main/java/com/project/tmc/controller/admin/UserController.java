package com.project.tmc.controller.admin;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.admin.UserDatatableRepository;
import com.project.tmc.model.user.Role;
import com.project.tmc.model.user.User;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
@Slf4j
public class UserController extends GenericCrudControllerImpl<User> {
    private final GenericCrudService<Role> roleService;

    public UserController(GenericCrudService<User> userService,
                          GenericCrudService<Role> roleService,
                          UserDatatableRepository datatableRepository) {
        super(userService, datatableRepository);

        this.roleService = roleService;
    }

    /**
     * Display index page
     * @return index view
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "admin/user/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<User> ajax(@Valid @RequestBody DataTablesInput input) {
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
    public ResponseEntity<Object> update(@ModelAttribute User model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute User model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
