package com.example.genealogictree.model.entities.people;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Setter
@Getter
@Table(name = "peoples")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "DATE")
    private LocalDate birth;

    @NotNull
    @Column(name = "activate")
    private boolean isActive;

//    private People biologicalFather;
//
//    private People biologicalMother;
//
//    private List<People> biolocicalChildrens;

    @ManyToOne
    private AdoptiveParents adoptiveParents;




}
