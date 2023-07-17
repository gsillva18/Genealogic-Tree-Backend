package com.example.genealogictree.model.entitygenealogictree;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 3)
    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "date")
    private LocalDate birth;

    @Lob
    private String image;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "person_biological_father")
    private Person biologicalFather;

    @ManyToOne
    @JoinColumn(name = "person_biological_mother")
    private Person biologicalMother;

    @OneToMany
    @JoinTable(name = "TB_BIOLOGICAL_CHILDREN", joinColumns = {
            @JoinColumn(name = "id_person_father_and_mother", referencedColumnName = "id")
    })
    private List<Person> biologicalChildren;

    @OneToMany
    @JoinTable(name = "TB_ADOPTIVE_CHILDREN", joinColumns = {
            @JoinColumn(name = "id_person_father_and_mother", referencedColumnName = "id")
    })
    private List<Person> adoptiveChildren;

    @ManyToMany
    @JoinTable(name = "TB_ADOPTIVE_PARENTS",
            joinColumns = {@JoinColumn(name = "id_person_adoptive_fk")},
            inverseJoinColumns = {@JoinColumn(name = "id_person_fk")})
    private List<Person> adoptiveParents;

}
