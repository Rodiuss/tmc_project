package com.project.tmc.repository.contrantor;

import com.project.tmc.model.contractor.ContractorStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorStatusRepository extends CrudRepository<ContractorStatus, Long> {
}
