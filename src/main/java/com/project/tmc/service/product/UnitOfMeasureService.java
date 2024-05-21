package com.project.tmc.service.product;

import com.project.tmc.model.product.UnitOfMeasure;
import com.project.tmc.repository.product.UnitOfMeasureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public Iterable<UnitOfMeasure> findAll() {
        return unitOfMeasureRepository.findAll();
    }

    public boolean isEmpty() {
        return !unitOfMeasureRepository.findAll().iterator().hasNext();
    }

    public UnitOfMeasure getById(Long id) throws Exception {
        return unitOfMeasureRepository.findById(id).orElseThrow(() -> (new Exception("Единица измерения не найдена")));
    }

    public void delete(Long id) throws Exception {
        getById(id);
        try {
            unitOfMeasureRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Единица измерения используется");
        }
    }

    public void save(UnitOfMeasure unitOfMeasure) throws Exception {
        if (unitOfMeasureRepository.findByName(unitOfMeasure.getName()).isPresent()) {
            throw new Exception("Единица измерения с таким наименованием уже существует.");
        }

        unitOfMeasureRepository.save(unitOfMeasure);
    }

    public void saveAll(List<UnitOfMeasure> unitsOfMeasure) {
        unitOfMeasureRepository.saveAll(unitsOfMeasure);
    }

    public void update(UnitOfMeasure unitOfMeasure) throws Exception {
        getById(unitOfMeasure.getId());
        unitOfMeasureRepository.save(unitOfMeasure);
    }
}
