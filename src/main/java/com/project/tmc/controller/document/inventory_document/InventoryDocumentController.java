package com.project.tmc.controller.document.inventory_document;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.document.inventory_document.InventoryDocumentDatatableRepository;
import com.project.tmc.model.contractor.Contractor;
import com.project.tmc.model.document.acceptance_document.AcceptanceDocument;
import com.project.tmc.model.document.inventory_document.InventoryDocument;
import com.project.tmc.model.document.shipment_document.ShipmentDocument;
import com.project.tmc.model.product.Product;
import com.project.tmc.repository.document.inventory_document.InventoryDocumentRepository;
import com.project.tmc.repository.document.shipment_document.ShipmentDocumentRepository;
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
@RequestMapping("/document/inventory_document")
public class InventoryDocumentController extends GenericCrudControllerImpl<InventoryDocument> {
    private final GenericCrudService<Product> productService;
    private final InventoryDocumentRepository inventoryDocumentRepository;

    public InventoryDocumentController(GenericCrudService<InventoryDocument> inventoryDocumentService,
                                       GenericCrudService<Product> productService,
                                       InventoryDocumentDatatableRepository repository,
                                       InventoryDocumentRepository inventoryDocumentRepository) {
        super(inventoryDocumentService, repository);

        this.productService = productService;
        this.inventoryDocumentRepository = inventoryDocumentRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", productService.findAll());

        return "document/inventory_document/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<InventoryDocument> ajax(@Valid @RequestBody DataTablesInput input) {
        return super.ajax(input);
    }

    @Override
    @GetMapping("/create")
    public ResponseEntity<Object> create() {
        InventoryDocument document = InventoryDocument.builder()
                .documentNumber(String.format("ИН-%03d/%s", inventoryDocumentRepository.getNextSeriesId(), LocalDate.now().getYear()))
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
    public ResponseEntity<Object> update(@ModelAttribute InventoryDocument model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute InventoryDocument model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
