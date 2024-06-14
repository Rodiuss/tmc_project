package com.project.tmc.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Transactional
@RequiredArgsConstructor
public class GenericCrudServiceImpl<T> implements GenericCrudService<T>{
    protected final CrudRepository<T, Long> repository;

    public Iterable<T> findAll() {
        return repository.findAll();
    }

    public boolean isEmpty() {
        return !repository.findAll().iterator().hasNext();
    }

    public T getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void save(T model) {
        repository.save(model);
    }

    public void saveAll(List<T> models) {
        repository.saveAll(models);
    }

    public void update(T model) {
        repository.save(model);
    }
}
