package com.project.tmc.controller.product;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.admin.ProductTypeDatatableRepository;
import com.project.tmc.model.product.ProductType;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product/product_types")
public class ProductTypeController extends GenericCrudControllerImpl<ProductType> {

    public ProductTypeController(GenericCrudService<ProductType> productTypeService, ProductTypeDatatableRepository dataTablesRepository) {
        super(productTypeService, dataTablesRepository);

    }

    @Override
    @GetMapping
    public String index() {
        return "product/product_type/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<ProductType> ajax(@Valid @RequestBody DataTablesInput input) {
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
    public ResponseEntity<Object> update(@ModelAttribute ProductType model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute ProductType model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
