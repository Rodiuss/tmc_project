package com.project.tmc.service.product;

import com.project.tmc.dto.ProductDto;
import com.project.tmc.dto.ProductGroupDto;
import com.project.tmc.model.product.Product;
import com.project.tmc.model.product.ProductGroup;
import com.project.tmc.model.product.UnitOfMeasure;
import com.project.tmc.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductGroupService productGroupService;
    private final UnitOfMeasureService unitOfMeasureService;

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public List<ProductDto> findAllDto() throws Exception {
        List<ProductDto> productsDto = new ArrayList<>();

        findAll().forEach((item) -> {
            try {
                productsDto.add(getDto(item));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return productsDto;
    }

    public Product getById(Long id) throws Exception {
        return productRepository.findById(id).orElseThrow(() -> (new Exception("Продукция не найдена")));
    }

    public void delete(Long id) throws Exception {
        getById(id);
        productRepository.deleteById(id);
    }

    public void save(Product product) throws Exception {
        if (productRepository.findByName(product.getName()).isPresent()) {
            throw new Exception("Продукция с таким наименованием уже существует.");
        }

        productRepository.save(product);
    }

    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }

    public void update(Product product) throws Exception {
        getById(product.getId());
        productRepository.save(product);
    }

    @SneakyThrows
    public ProductDto getDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .article(product.getArticle())
                .code(product.getCode())
                .inStock(product.getInStock())
                .group(productGroupService.getDtoById(product.getGroup().getId()))
                .purchasePrice(product.getPurchasePrice())
                .sellingPrice(product.getSellingPrice())
                .quantity(product.getQuantity())
                .unit(unitOfMeasureService.getById(product.getUnit().getId()))
                .type(product.getType())
                .excisable(product.getExcisable())
                .description(product.getDescription())
                .barcode(product.getBarcode())
                .vat(product.getVat())
                .build();
    }

    public ProductDto getDtoById(Long id) throws Exception {
        return getDto(getById(id));
    }
}
