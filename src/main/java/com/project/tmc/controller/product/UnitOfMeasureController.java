package com.project.tmc.controller.product;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.product.UnitOfMeasureDataTableRepository;
import com.project.tmc.model.product.UnitOfMeasure;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("product/units_of_measure")
public class UnitOfMeasureController extends GenericCrudControllerImpl<UnitOfMeasure> {
    public UnitOfMeasureController(GenericCrudService<UnitOfMeasure> unitOfMeasureService, UnitOfMeasureDataTableRepository dataTablesRepository) {
        super(unitOfMeasureService, dataTablesRepository);
    }

    @Override
    @GetMapping
    public String index() {
        return "product/unit_of_measure/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<UnitOfMeasure> ajax(@Valid @RequestBody DataTablesInput input) {
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
    public ResponseEntity<Object> update(@ModelAttribute UnitOfMeasure model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute UnitOfMeasure model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
