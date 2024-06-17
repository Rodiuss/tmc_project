package com.project.tmc.controller.document.acceptance_document;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.document.acceptance_document.AcceptanceDocumentDatatableRepository;
import com.project.tmc.model.contractor.Contractor;
import com.project.tmc.model.document.acceptance_document.AcceptanceDocument;
import com.project.tmc.model.product.Product;
import com.project.tmc.repository.document.acceptance_document.AcceptanceDocumentRepository;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping("/document/acceptance_document")
public class AcceptanceDocumentController extends GenericCrudControllerImpl<AcceptanceDocument> {
    private final GenericCrudService<Contractor> contractorService;
    private final GenericCrudService<Product> productService;

    private final AcceptanceDocumentRepository acceptanceDocumentRepository;

    public AcceptanceDocumentController(GenericCrudService<AcceptanceDocument> acceptanceDocumentService,
                                        GenericCrudService<Contractor> contractorService,
                                        GenericCrudService<Product> productService,
                                        AcceptanceDocumentDatatableRepository repository,
                                        AcceptanceDocumentRepository acceptanceDocumentRepository) {
        super(acceptanceDocumentService, repository);

        this.contractorService = contractorService;
        this.productService = productService;
        this.acceptanceDocumentRepository = acceptanceDocumentRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("contractors", contractorService.findAll());
        model.addAttribute("products", productService.findAll());
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
        AcceptanceDocument document = AcceptanceDocument.builder()
                .documentNumber(String.format("ПТ-%03d/%s", acceptanceDocumentRepository.getNextSeriesId(), LocalDate.now().getYear()))
                .documentDate(Date.valueOf(LocalDate.now()))
                .build();

        service.save(document);

        return ResponseEntity.ok().body(document);
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
