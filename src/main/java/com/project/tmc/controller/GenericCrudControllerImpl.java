package com.project.tmc.controller;

import com.project.tmc.service.GenericCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Slf4j
public class GenericCrudControllerImpl<T> {
    protected final GenericCrudService<T> service;
    protected final DataTablesRepository<T, Long> dataTablesRepository;

    public String index() {
        return null;
    }

    public DataTablesOutput<T> ajax(DataTablesInput input) {
        return dataTablesRepository.findAll(input);
    }

    public ResponseEntity<Object> create() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> edit(Long id) {
        try {
            return ResponseEntity.ok().body(service.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
    }

    public ResponseEntity<Object> update(T role) {
        try {
            service.update(role);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body("Запись со схожими атрибутами уже существует");
        }
        return ResponseEntity.ok().body(role);
    }

    public ResponseEntity<Object> store(T role) {
        try {
            service.save(role);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(role);
    }

    public ResponseEntity<Object> destroy(Long id) {
        try {
            service.delete(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(e.getMessage());
        }
        return ResponseEntity.ok().body(id);
    }
}
