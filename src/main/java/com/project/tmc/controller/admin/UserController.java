package com.project.tmc.controller.admin;

import com.project.tmc.datatable.admin.UserDatatableRepository;
import com.project.tmc.dto.UserDto;
import com.project.tmc.model.admin.User;
import com.project.tmc.service.admin.RoleService;
import com.project.tmc.service.admin.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserDatatableRepository userDatatableRepository;

    /**
     * Display index page
     * @return index view
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "admin/user/index";
    }

    /**
     * Processing datatable information
     * @param input table filter information
     * @return filtered information
     */
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<UserDto> ajax(@Valid @RequestBody DataTablesInput input) {
        return userDatatableRepository.findAll(input, userService::getDto);
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
            return ResponseEntity.ok().body(userService.getDtoById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
    }

    /**
     * Update the model
     * @param user model`s data
     * @return good or bad response
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@ModelAttribute User user) {
        try {
            userService.update(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(user);
    }

    /**
     * Store a new model
     * @param user model`s data
     * @return good or bad response
     */
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute User user) {
        try {
            userService.save(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }

        return ResponseEntity.ok().body(user);
    }

    /**
     * Delete an existing model
     * @param id model`s id for delete
     * @return good or bad response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }

        return ResponseEntity.ok().body(id);
    }
}
