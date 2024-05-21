package com.project.tmc.service.product;

import com.project.tmc.datatable.product.VatDatatableRepository;
import com.project.tmc.model.product.Vat;
import com.project.tmc.repository.product.VatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VatService {
    private final VatRepository vatRepository;

    public Iterable<Vat> findAll() {
        return vatRepository.findAll();
    }

    public boolean isEmpty() {
        return !vatRepository.findAll().iterator().hasNext();
    }

    public Vat getById(String id) throws Exception {
        return vatRepository.findById(id).orElseThrow(() -> (new Exception("НДС не найдена")));
    }

    public void delete(String id) throws Exception {
        getById(id);
        try {
            vatRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("НДС используется");
        }
    }

    public void save(Vat vat) throws Exception {
        if (vatRepository.findById(vat.getProgramName()).isPresent()) {
            throw new Exception("НДС с таким программным наименованием уже существует");
        }

        vatRepository.save(vat);
    }

    public void saveAll(List<Vat> vats) {
        vatRepository.saveAll(vats);
    }

    public void update(Vat vat) throws Exception {
        getById(vat.getProgramName());
        vatRepository.save(vat);
    }
}
