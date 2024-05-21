package com.project.tmc.dto;

import com.project.tmc.model.product.ProductType;
import com.project.tmc.model.product.UnitOfMeasure;
import com.project.tmc.model.product.Vat;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDto {
    private Long id;
    private String name;
    private String article;
    private String code;
    private Boolean inStock;
    private ProductGroupDto group;
    private Double purchasePrice;
    private Double sellingPrice;
    private Integer quantity;
    private UnitOfMeasure unit;
    private ProductType type;
    private Boolean excisable;
    private String description;
    private String barcode;
    private Vat vat;
}
