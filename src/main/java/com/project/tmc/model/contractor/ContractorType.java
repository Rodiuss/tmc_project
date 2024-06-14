package com.project.tmc.model.contractor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tmc_provider_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractorType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "providerType", cascade = CascadeType.REMOVE)
    private List<Contractor> contractorList;
}
