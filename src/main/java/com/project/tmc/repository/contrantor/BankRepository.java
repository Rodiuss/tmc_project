package com.project.tmc.repository.contrantor;

import com.project.tmc.model.contractor.Bank;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends CrudRepository<Bank, Long> {
    Optional<Bank> findByName(String name);
}
