package com.project.tmc.model.contractor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tmc_contractor_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractorStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "contractorStatus", cascade = CascadeType.REMOVE)
    private List<Contractor> contractorList;
}
