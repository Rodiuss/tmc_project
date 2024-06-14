package com.project.tmc.repository.contrantor.provider;

import com.project.tmc.model.contractor.ContactPersonPosition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactPersonPositionRepository extends CrudRepository<ContactPersonPosition, Long> {
}
