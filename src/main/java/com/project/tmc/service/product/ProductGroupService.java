package com.project.tmc.service.product;

import com.project.tmc.dto.ProductGroupDto;
import com.project.tmc.model.product.ProductGroup;
import com.project.tmc.repository.product.ProductGroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;

    public Iterable<ProductGroup> findAll() {
        return productGroupRepository.findAll();
    }

    public List<ProductGroupDto> findAllDto() {
        Iterable<ProductGroup> productGroups = findAll();
        List<ProductGroupDto> productGroupsDto = new ArrayList<>();
        productGroups.forEach((item) -> productGroupsDto.add(getDto(item)));

        return productGroupsDto;
    }

    public boolean isEmpty() {
        return !findAll().iterator().hasNext();
    }

    public ProductGroup getById(Long id) throws Exception {
        return productGroupRepository.findById(id).orElseThrow(() -> (new Exception("Группа товаров не найдена")));
    }

    public void delete(Long id) throws Exception {
        getById(id);
        try {
            productGroupRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Группа товаров используется");
        }
    }

    public void save(ProductGroup productGroup) throws Exception {
        if (productGroupRepository.findByName(productGroup.getName()).isPresent()) {
            throw new Exception("Группа товаров с таким наименованием уже существует.");
        }

        productGroupRepository.save(productGroup);
    }

    public void saveAll(List<ProductGroup> productGroups) {
        productGroupRepository.saveAll(productGroups);
    }

    public void update(ProductGroup productGroup) throws Exception {
        getById(productGroup.getId());

        productGroupRepository.save(productGroup);
    }

    public ProductGroupDto getDto(ProductGroup productGroup) {
        return ProductGroupDto.builder()
                .id(productGroup.getId())
                .name(productGroup.getName())
                .parentGroup(ProductGroupDto.builder()
                        .id(productGroup.getParentGroup() != null ? productGroup.getParentGroup().getId() : null)
                        .name(productGroup.getParentGroup() != null ? productGroup.getParentGroup().getName() : null)
                        .build())
                .path(productGroupRepository.findPath(productGroup.getId()))
                .build();
    }

    public ProductGroupDto getDtoById(Long id) throws Exception {
        return getDto(getById(id));
    }
}
