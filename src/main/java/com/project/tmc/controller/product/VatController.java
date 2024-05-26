package com.project.tmc.controller.product;

import com.project.tmc.datatable.admin.VatDatatableRepository;
import com.project.tmc.model.admin.Vat;
import com.project.tmc.service.admin.VatService;
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
@RequestMapping("/product/vat")
public class VatController {
    private final VatService vatService;
    private final VatDatatableRepository vatDatatableRepository;

    @GetMapping
    public String index() {
        return "product/vat/index";
    }

    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<Vat> ajax(@Valid @RequestBody DataTablesInput input) {
        return vatDatatableRepository.findAll(input);
    }

    @GetMapping("/create")
    public ResponseEntity<Object> create() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{programName}")
    public ResponseEntity<Object> edit(@PathVariable String programName) {
        try {
            return ResponseEntity.ok().body(vatService.getById(programName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@ModelAttribute Vat vat) {
        try {
            vatService.update(vat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute Vat vat) {
        try {
            vatService.save(vat);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{programName}")
    public ResponseEntity<Object> destroy(@PathVariable String programName) {
        try {
            vatService.delete(programName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
