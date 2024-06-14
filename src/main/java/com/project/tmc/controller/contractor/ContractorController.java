package com.project.tmc.controller.contractor;

import com.project.tmc.controller.GenericCrudControllerImpl;
import com.project.tmc.datatable.contractor.provider.ProviderDatatableRepository;
import com.project.tmc.model.contractor.Bank;
import com.project.tmc.model.contractor.ContractorStatus;
import com.project.tmc.model.contractor.ContractorType;
import com.project.tmc.model.contractor.Contractor;
import com.project.tmc.service.GenericCrudService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contractors")
public class ContractorController extends GenericCrudControllerImpl<Contractor> {
    private final GenericCrudService<ContractorType> contractorTypeService;
    private final GenericCrudService<Bank> bankService;
    private final GenericCrudService<ContractorStatus> contractorStatusService;

    public ContractorController(GenericCrudService<Contractor> providerService,
                                GenericCrudService<ContractorType> contractorTypeService,
                                GenericCrudService<Bank> bankService,
                                GenericCrudService<ContractorStatus> contractorStatusService,
                                ProviderDatatableRepository dataTablesRepository) {
        super(providerService, dataTablesRepository);

        this.contractorTypeService = contractorTypeService;
        this.bankService = bankService;
        this.contractorStatusService = contractorStatusService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("providerTypes", contractorTypeService.findAll());
        model.addAttribute("banks", bankService.findAll());
        model.addAttribute("contractorStatuses", contractorStatusService.findAll());

        return "contractor/contractor/index";
    }

    @Override
    @PostMapping("/ajax")
    public @ResponseBody DataTablesOutput<Contractor> ajax(@Valid @RequestBody DataTablesInput input) {
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
    public ResponseEntity<Object> update(@ModelAttribute Contractor model) {
        return super.update(model);
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> store(@ModelAttribute Contractor model) {
        return super.store(model);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id) {
        return super.destroy(id);
    }
}
