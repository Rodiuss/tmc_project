package com.project.tmc.controller.product;

import com.project.tmc.datatable.product.ProductDataTableRepository;
import com.project.tmc.dto.ProductDto;
import com.project.tmc.model.product.Product;
import com.project.tmc.service.admin.ProductTypeService;
import com.project.tmc.service.admin.VatService;
import com.project.tmc.service.product.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final ProductGroupService productGroupService;
    private final ProductDataTableRepository productDataTableRepository;
    private final VatService vatService;
    private final ProductTypeService productTypeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("units", unitOfMeasureService.findAll());
        model.addAttribute("productGroups", productGroupService.findAll());
        model.addAttribute("vats", vatService.findAll());
        model.addAttribute("productTypes", productTypeService.findAll());
        return "product/product/index";
    }

    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<ProductDto> ajax(@Valid @RequestBody DataTablesInput input) {
        return productDataTableRepository.findAll(input, productService::getDto);
    }

    @GetMapping("/create")
    public ResponseEntity<Object> create() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> edit(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(productService.getDtoById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@ModelAttribute Product role) {
        try {
            productService.update(role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute Product role) {
        try {
            productService.save(role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        try {
            productService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(id);
    }
}
