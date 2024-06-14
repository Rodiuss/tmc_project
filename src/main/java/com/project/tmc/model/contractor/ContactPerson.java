package com.project.tmc.model.contractor;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tmc_contact_person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="surname")
    private String surname;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="patronymic", nullable = false)
    private String patronymic;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "contactor_id", referencedColumnName = "id")
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private ContactPersonPosition contactPersonPosition;
}
