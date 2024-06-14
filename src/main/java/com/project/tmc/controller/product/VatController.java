package com.project.tmc.controller.product;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.admin.VatDatatableRepository;
import com.project.tmc.model.product.Vat;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product/vats")
public class VatController extends GenericCrudControllerImpl<Vat> {
    public VatController(GenericCrudService<Vat> vatService, VatDatatableRepository dataTablesRepository) {
        super(vatService, dataTablesRepository);
    }

    @Override
    @GetMapping
    public String index() {
        return "product/vat/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<Vat> ajax(@Valid @RequestBody DataTablesInput input) {
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
    public ResponseEntity<Object> update(@ModelAttribute Vat model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute Vat model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
