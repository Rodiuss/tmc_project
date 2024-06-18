package com.project.tmc.controller.product;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.product.ProductDataTableRepository;
import com.project.tmc.model.product.ProductType;
import com.project.tmc.model.product.Vat;
import com.project.tmc.model.product.Product;
import com.project.tmc.model.product.ProductGroup;
import com.project.tmc.model.product.UnitOfMeasure;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController extends GenericCrudControllerImpl<Product> {
    private final GenericCrudService<UnitOfMeasure> unitOfMeasureService;
    private final GenericCrudService<ProductGroup> productGroupService;
    private final GenericCrudService<Vat> vatService;
    private final GenericCrudService<ProductType> productTypeService;

    public ProductController(GenericCrudService<Product> productService,
                             GenericCrudService<UnitOfMeasure> unitOfMeasureService,
                             GenericCrudService<ProductGroup> productGroupService,
                             GenericCrudService<Vat> vatService,
                             GenericCrudService<ProductType> productTypeService,
                             ProductDataTableRepository dataTableRepository) {
        super(productService, dataTableRepository);

        this.unitOfMeasureService = unitOfMeasureService;
        this.productGroupService = productGroupService;
        this.vatService = vatService;
        this.productTypeService = productTypeService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("units", unitOfMeasureService.findAll());
        model.addAttribute("productGroups", productGroupService.findAll());
        model.addAttribute("vats", vatService.findAll());
        model.addAttribute("productTypes", productTypeService.findAll());
        return "product/product/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<Product> ajax(@Valid @RequestBody DataTablesInput input) {
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
    public ResponseEntity<Object> update(@ModelAttribute Product model) {
        try {
            model.setQuantity(service.getById(model.getId()).getQuantity());
            service.update(model);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body("Запись со схожими атрибутами уже существует");
        }
        return ResponseEntity.ok().body(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute Product model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
