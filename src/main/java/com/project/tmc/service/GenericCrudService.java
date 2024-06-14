package com.project.tmc.service;

import java.util.List;

public interface GenericCrudService<T> {
    Iterable<T> findAll();

    boolean isEmpty();

    T getById(Long id) throws Exception;

    void delete(Long id);

    void save(T model);

    void saveAll(List<T> models);

    void update(T model);
}
