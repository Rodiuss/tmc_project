package com.project.tmc.controller.document.acceptance_document;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.document.acceptance_document.AcceptanceDocumentDatatableRepository;
import com.project.tmc.model.contractor.Contractor;
import com.project.tmc.model.document.acceptance_document.AcceptanceDocument;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/document/acceptance_document")
public class AcceptanceDocumentController extends GenericCrudControllerImpl<AcceptanceDocument> {
    private final GenericCrudService<Contractor> contractorService;

    public AcceptanceDocumentController(GenericCrudService<AcceptanceDocument> acceptanceDocumentService,
                                        GenericCrudService<Contractor> contractorService,
                                        AcceptanceDocumentDatatableRepository repository) {
        super(acceptanceDocumentService, repository);

        this.contractorService = contractorService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("contractors", contractorService.findAll());
        return "document/acceptance_document/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<AcceptanceDocument> ajax(@Valid @RequestBody DataTablesInput input) {
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
    public ResponseEntity<Object> update(@ModelAttribute AcceptanceDocument model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute AcceptanceDocument model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
