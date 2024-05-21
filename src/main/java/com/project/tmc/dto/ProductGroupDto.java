package com.project.tmc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductGroupDto {
    private Long id;
    private String name;
    private ProductGroupDto parentGroup;
    private String path;
}
