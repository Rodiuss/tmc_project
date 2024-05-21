package com.project.tmc.controller.product;

import com.project.tmc.datatable.product.UnitOfMeasureDataTableRepository;
import com.project.tmc.model.product.UnitOfMeasure;
import com.project.tmc.service.product.UnitOfMeasureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("product/unit_of_measure")
@RequiredArgsConstructor
public class UnitOfMeasureController {
    private final UnitOfMeasureService unitOfMeasureService;
    private final UnitOfMeasureDataTableRepository unitOfMeasureDataTableRepository;

    @GetMapping
    public String index() {
        return "product/unit_of_measure/index";
    }

    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<UnitOfMeasure> ajax(@Valid @RequestBody DataTablesInput input) {
        return unitOfMeasureDataTableRepository.findAll(input);
    }

    @GetMapping("/create")
    public ResponseEntity<Object> create() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> edit(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(unitOfMeasureService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@ModelAttribute UnitOfMeasure role) {
        try {
            unitOfMeasureService.update(role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(role);
    }

    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute UnitOfMeasure role) {
        try {
            unitOfMeasureService.save(role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        try {
            unitOfMeasureService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(id);
    }
}
