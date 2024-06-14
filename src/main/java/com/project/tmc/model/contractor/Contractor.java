package com.project.tmc.model.contractor;

import com.fasterxml.jackson.annotation.*;
import com.project.tmc.model.contractor.Bank;
import com.project.tmc.model.contractor.ContactPerson;
import com.project.tmc.model.contractor.ContractorStatus;
import com.project.tmc.model.contractor.ContractorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tmc_contractor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "legal_address")
    private String legalAddress;

    @Column(name = "actual_address")
    private String actualAddress;

    // ИНН (индивидуальный номер налогоплательщика)
    @Column(name = "tin", nullable = false, unique = true)
    private String tin;

    // КПП (код причины постановки на учет)
    @Column(name = "kpp", nullable = false, unique = true)
    private String kpp;

    // ОГРН — (основной государственный регистрационный номер)
    @Column(name = "ogrn", nullable = false, unique = true)
    private String ogrn;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;

    @Column(name = "account_number")
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private ContractorType providerType;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private ContractorStatus contractorStatus;

    @JsonManagedReference
    @OneToMany(mappedBy = "contractor", cascade = CascadeType.REMOVE)
    private List<ContactPerson> contactPersonList;
}
