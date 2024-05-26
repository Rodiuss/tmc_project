package com.project.tmc.controller.product;

import com.project.tmc.datatable.admin.ProductTypeDatatableRepository;
import com.project.tmc.model.admin.ProductType;
import com.project.tmc.service.admin.ProductTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product/product_type")
public class ProductTypeController {
    private final ProductTypeService productTypeService;
    private final ProductTypeDatatableRepository productTypeDatatableRepository;

    @GetMapping
    public String index() {
        return "product/product_type/index";
    }

    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<ProductType> ajax(@Valid @RequestBody DataTablesInput input) {
        return productTypeDatatableRepository.findAll(input);
    }

    @GetMapping("/create")
    public ResponseEntity<Object> create() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{programName}")
    public ResponseEntity<Object> edit(@PathVariable String programName) {
        try {
            return ResponseEntity.ok().body(productTypeService.getById(programName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@ModelAttribute ProductType productType) {
        try {
            productTypeService.update(productType);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(productType);
    }

    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute ProductType productType) {
        try {
            productTypeService.save(productType);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(productType);
    }

    @DeleteMapping("/{programName}")
    public ResponseEntity<Object> destroy(@PathVariable String programName) {
        try {
            productTypeService.delete(programName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
