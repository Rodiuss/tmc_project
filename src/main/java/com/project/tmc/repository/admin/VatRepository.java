package com.project.tmc.repository.admin;

import com.project.tmc.model.product.Vat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VatRepository extends CrudRepository<Vat, Long> {
    Optional<Vat> findByProgramName(String name);
}
