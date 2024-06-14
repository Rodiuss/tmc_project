package com.project.tmc.controller.product;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.product.ProductGroupDataTableRepository;
import com.project.tmc.model.product.ProductGroup;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("product/product_groups")
@Slf4j
public class ProductGroupController extends GenericCrudControllerImpl<ProductGroup> {
    public ProductGroupController(GenericCrudService<ProductGroup> productGroupService, ProductGroupDataTableRepository dataTableRepository) {
        super(productGroupService, dataTableRepository);
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("groups", service.findAll());
        return "product/product_group/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<ProductGroup> ajax(@Valid @RequestBody DataTablesInput input) {
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
    public ResponseEntity<Object> update(@ModelAttribute ProductGroup model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute ProductGroup model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
