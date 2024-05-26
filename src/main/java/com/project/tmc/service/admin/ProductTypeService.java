package com.project.tmc.service.admin;

import com.project.tmc.model.admin.ProductType;
import com.project.tmc.repository.admin.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    public Iterable<ProductType> findAll() {
        return productTypeRepository.findAll();
    }

    public boolean isEmpty() {
        return !productTypeRepository.findAll().iterator().hasNext();
    }

    public ProductType getById(String programName) throws Exception {
        return productTypeRepository.findById(programName).orElseThrow(() -> (new Exception("Тип продукции не найден")));
    }

    public void delete(String programName) throws Exception {
        getById(programName);
        try {
            productTypeRepository.deleteById(programName);
        } catch (Exception e) {
            throw new Exception("Тип продукции используется");
        }
    }

    public void save(ProductType productType) throws Exception {
        if (productTypeRepository.findById(productType.getProgramName()).isPresent()) {
            throw new Exception("Тип продукции с таким наименованием уже существует.");
        }

        productTypeRepository.save(productType);
    }

    public void saveAll(List<ProductType> unitsOfMeasure) {
        productTypeRepository.saveAll(unitsOfMeasure);
    }

    public void update(ProductType productType) throws Exception {
        getById(productType.getProgramName());
        productTypeRepository.save(productType);
    }
}
