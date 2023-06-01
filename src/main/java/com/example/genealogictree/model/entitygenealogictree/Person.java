package com.example.genealogictree.model.entitygenealogictree;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "TB_PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "date")
    private LocalDate birth;

    @NotNull
    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    private Person biologicalFather;

    @ManyToOne
    private Person biologicalMother;

    @OneToMany
    private List<Person> biologicalChildren;

    @ManyToMany
    private List<Person> adoptiveChildren;

    @OneToMany
    private List<AdoptiveParents> adoptiveParents;




}
