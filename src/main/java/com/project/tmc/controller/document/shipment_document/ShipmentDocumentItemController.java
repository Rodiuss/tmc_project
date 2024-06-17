package com.project.tmc.controller.document.shipment_document;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.controller.document.acceptance_document.AcceptanceDocumentItemSpec;
import com.project.tmc.datatable.document.shipment_document.ShipmentDocumentItemDatatableRepository;
import com.project.tmc.model.document.acceptance_document.AcceptanceDocumentItem;
import com.project.tmc.model.document.shipment_document.ShipmentDocument;
import com.project.tmc.model.document.shipment_document.ShipmentDocumentItem;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/document/shipment_document_item")
public class ShipmentDocumentItemController extends GenericCrudControllerImpl<ShipmentDocumentItem> {
    public ShipmentDocumentItemController(GenericCrudService<ShipmentDocumentItem> shipmentDocumentItemService, ShipmentDocumentItemDatatableRepository repository) {
        super(shipmentDocumentItemService, repository);
    }

    @PostMapping("/ajax/{id}")
    public @ResponseBody DataTablesOutput<ShipmentDocumentItem> ajax(@Valid @RequestBody DataTablesInput input, @PathVariable Long id) {
        return dataTablesRepository.findAll(input, new ShipmentDocumentItemSpec(id));
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
    public ResponseEntity<Object> update(@ModelAttribute ShipmentDocumentItem model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute ShipmentDocumentItem model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
