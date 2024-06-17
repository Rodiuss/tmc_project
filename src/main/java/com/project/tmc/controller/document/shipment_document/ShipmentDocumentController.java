package com.project.tmc.controller.document.shipment_document;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.document.shipment_document.ShipmentDocumentDatatableRepository;
import com.project.tmc.model.contractor.Contractor;
import com.project.tmc.model.document.shipment_document.ShipmentDocument;
import com.project.tmc.model.product.Product;
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
@RequestMapping("/document/shipment_document")
public class ShipmentDocumentController extends GenericCrudControllerImpl<ShipmentDocument> {
    private final GenericCrudService<Contractor> contractorService;
    private final GenericCrudService<Product> productService;

    private final ShipmentDocumentRepository shipmentDocumentRepository;

    public ShipmentDocumentController(GenericCrudService<ShipmentDocument> shipmentDocumentService,
                                      GenericCrudService<Contractor> contractorService,
                                      GenericCrudService<Product> productService,
                                      ShipmentDocumentDatatableRepository repository,
                                      ShipmentDocumentRepository shipmentDocumentRepository) {
        super(shipmentDocumentService, repository);

        this.contractorService = contractorService;
        this.productService = productService;
        this.shipmentDocumentRepository = shipmentDocumentRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("contractors", contractorService.findAll());
        model.addAttribute("products", productService.findAll());

        return "document/shipment_document/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<ShipmentDocument> ajax(@Valid @RequestBody DataTablesInput input) {
        return super.ajax(input);
    }

    @GetMapping("/create")
    public ResponseEntity<Object> create() {
        ShipmentDocument document = ShipmentDocument.builder()
                .documentDate(Date.valueOf(LocalDate.now()))
                .documentNumber(String.format("ОТ-%03d/%s", shipmentDocumentRepository.getNextSeriesId(), LocalDate.now().getYear()))
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
    public ResponseEntity<Object> update(@ModelAttribute ShipmentDocument model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute ShipmentDocument model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
