package com.project.tmc.controller.product;

import com.project.tmc.datatable.product.ProductGroupDataTableRepository;
import com.project.tmc.dto.ProductGroupDto;
import com.project.tmc.model.product.ProductGroup;
import com.project.tmc.service.product.ProductGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("product/product_groups")
@Slf4j
public class ProductGroupController {
    private final ProductGroupService productGroupService;
    private final ProductGroupDataTableRepository productGroupDataTableRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("groups", productGroupService.findAllDto());

        return "product/product_group/index";
    }

    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<ProductGroupDto> ajax(@Valid @RequestBody DataTablesInput input) {
        return productGroupDataTableRepository.findAll(input, productGroupService::getDto);
    }

    @GetMapping("/create")
    public ResponseEntity<Object> create() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> edit(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(productGroupService.getDtoById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@ModelAttribute ProductGroup productGroup) {
        try {
            productGroupService.update(productGroup);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute ProductGroup productGroup) {
        try {
            productGroupService.save(productGroup);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        try {
            productGroupService.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(id);
    }
}
