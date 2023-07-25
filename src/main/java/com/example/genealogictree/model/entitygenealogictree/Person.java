package com.example.genealogictree.model.entitygenealogictree;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Entity
@Table(name = "TB_PERSON")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotEmpty
    @Size(min = 3)
    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "date")
    private LocalDate birth;

    @Lob
    private String image;

    @Column(name = "is_active")
    private boolean isActive;

    private Integer layer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_biological_father")
    private Person biologicalFather;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_biological_mother")
    private Person biologicalMother;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TB_BIOLOGICAL_CHILDREN",
            joinColumns = {@JoinColumn(name = "id_person_father_and_mother")},
            inverseJoinColumns = {@JoinColumn(name = "id_person_biological_children")}
    )
    private List<Person> biologicalChildren = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TB_ADOPTIVE_CHILDREN",
            joinColumns = { @JoinColumn(name = "id_person_father_and_mother")},
            inverseJoinColumns = {@JoinColumn(name = "id_person_adoptive_children")}
    )
    private List<Person> adoptiveChildren = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TB_ADOPTIVE_PARENTS",
            joinColumns = {@JoinColumn(name = "id_person_adoptive_fk")},
            inverseJoinColumns = {@JoinColumn(name = "id_person_father_adoptive_fk")})
    private List<Person> adoptiveParents = new ArrayList<>();

    public Person(String name, Boolean isActive, Integer layer){
        this.name = name;
        this.isActive = isActive;
        this.layer = layer;
    }

}
