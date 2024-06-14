package com.project.tmc.repository.contrantor.provider;

import com.project.tmc.model.contractor.ContactPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactPersonRepository extends CrudRepository<ContactPerson, Long> {
}
