package com.project.tmc.repository.contrantor;

import com.project.tmc.model.contractor.ContractorType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorTypeRepository extends CrudRepository<ContractorType, Long> {
}
