package com.project.tmc.controller.document.shipment_document;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.document.shipment_document.ShipmentDocumentDatatableRepository;
import com.project.tmc.model.document.shipment_document.ShipmentDocument;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/document/shipment_document")
public class ShipmentDocumentController extends GenericCrudControllerImpl<ShipmentDocument> {
    public ShipmentDocumentController(GenericCrudService<ShipmentDocument> shipmentDocumentService, ShipmentDocumentDatatableRepository repository) {
        super(shipmentDocumentService, repository);
    }

    @Override
    @GetMapping
    public String index() {
        return "contractor/bank/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<ShipmentDocument> ajax(@Valid @RequestBody DataTablesInput input) {
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
