package com.example.genealogictree.model.entitygenealogictree;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@Entity
@Table(name = "TB_GENEALOGIC_TREE")
public class GenealogicTree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 4)
    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private LocalDate creationDate = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "initial_person_fk")
    private Person initialPerson;
}
