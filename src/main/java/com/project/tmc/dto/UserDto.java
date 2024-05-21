package com.project.tmc.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String surname;
    private String name;
    private String patronymic;
    private String fullName;
    private String individualTaxpayerNumber;
    private List<Long> roles;
}
