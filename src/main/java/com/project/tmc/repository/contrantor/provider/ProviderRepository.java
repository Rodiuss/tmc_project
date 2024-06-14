package com.project.tmc.repository.contrantor.provider;

import com.project.tmc.model.contractor.Contractor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends CrudRepository<Contractor, Long> {
}
