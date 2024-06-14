package com.project.tmc.model.contractor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tmc_contact_person_position")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactPersonPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "contactPersonPosition", cascade = CascadeType.REMOVE)
    private List<ContactPerson> contactPersonList;
}
