package com.project.tmc.controller.contractor;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.contractor.provider.ContactPersonDatatableRepository;
import com.project.tmc.model.contractor.ContactPerson;
import com.project.tmc.model.contractor.ContactPersonPosition;
import com.project.tmc.model.contractor.Contractor;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contractor/contact_persons")
public class ContactPersonController extends GenericCrudControllerImpl<ContactPerson> {
    private final GenericCrudService<ContactPersonPosition> contactPersonPositionService;
    private final GenericCrudService<Contractor> providerService;

    public ContactPersonController(GenericCrudService<ContactPerson> service,
                                   GenericCrudService<ContactPersonPosition> contactPersonPositionService,
                                   GenericCrudService<Contractor> providerService,
                                   ContactPersonDatatableRepository dataTablesRepository) {
        super(service, dataTablesRepository);

        this.contactPersonPositionService = contactPersonPositionService;
        this.providerService = providerService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("positions", contactPersonPositionService.findAll());
        model.addAttribute("providers", providerService.findAll());
        return "contractor/contact_person/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<ContactPerson> ajax(@Valid @RequestBody DataTablesInput input) {
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
    public ResponseEntity<Object> update(@ModelAttribute ContactPerson model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute ContactPerson model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
