package com.example.genealogictree.model.entitygenealogictree;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "TB_GENEALOGIC_TREE")
public class GenealogicTree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Value("#{T(java.time.LocalDate).now()}")
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @OneToOne
    private Person initialPerson;
}
