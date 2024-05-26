package com.project.tmc.repository.admin;

import com.project.tmc.model.admin.Vat;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VatRepository extends CrudRepository<Vat, String> {
    Optional<Vat> findByProgramName(String name);
}
