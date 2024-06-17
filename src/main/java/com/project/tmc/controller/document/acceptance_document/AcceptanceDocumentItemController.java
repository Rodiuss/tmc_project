package com.project.tmc.controller.document.acceptance_document;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.document.acceptance_document.AcceptanceDocumentItemDatatableRepository;
import com.project.tmc.model.document.acceptance_document.AcceptanceDocumentItem;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/document/acceptance_document_item")
public class AcceptanceDocumentItemController extends GenericCrudControllerImpl<AcceptanceDocumentItem> {
    public AcceptanceDocumentItemController(GenericCrudService<AcceptanceDocumentItem> acceptanceDocumentItemService, AcceptanceDocumentItemDatatableRepository repository) {
        super(acceptanceDocumentItemService, repository);
    }

    @PostMapping("/ajax/{id}")
    public @ResponseBody DataTablesOutput<AcceptanceDocumentItem> ajax(@Valid @RequestBody DataTablesInput input, @PathVariable Long id) {
        return dataTablesRepository.findAll(input, new AcceptanceDocumentItemSpec(id));
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
    public ResponseEntity<Object> update(@ModelAttribute AcceptanceDocumentItem model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute AcceptanceDocumentItem model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
