package com.project.tmc.repository.product;

import com.project.tmc.model.product.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    public Optional<UnitOfMeasure> findByName(String name);
}
