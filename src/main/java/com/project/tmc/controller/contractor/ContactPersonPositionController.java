package com.project.tmc.controller.contractor;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.contractor.provider.ContactPersonPositionDatatableRepository;
import com.project.tmc.model.contractor.ContactPersonPosition;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contractor/contact_person_positions")
public class ContactPersonPositionController extends GenericCrudControllerImpl<ContactPersonPosition> {
    public ContactPersonPositionController(GenericCrudService<ContactPersonPosition> service, ContactPersonPositionDatatableRepository dataTablesRepository) {
        super(service, dataTablesRepository);
    }

    @Override
    @GetMapping
    public String index() {
        return "contractor/contact_person_position/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<ContactPersonPosition> ajax(@Valid @RequestBody DataTablesInput input) {
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
    public ResponseEntity<Object> update(@ModelAttribute ContactPersonPosition model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute ContactPersonPosition model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
