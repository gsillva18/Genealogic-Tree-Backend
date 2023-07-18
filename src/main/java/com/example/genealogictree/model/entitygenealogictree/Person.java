package com.example.genealogictree.model.entitygenealogictree;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_PERSON")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "date")
    private LocalDate birth;

    @Lob
    private String image;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_biological_father")
    private Person biologicalFather;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_biological_mother")
    private Person biologicalMother;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "TB_BIOLOGICAL_CHILDREN", joinColumns = {
            @JoinColumn(name = "id_person_father_and_mother", referencedColumnName = "id")
    })
    private List<Person> biologicalChildren;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "TB_ADOPTIVE_CHILDREN", joinColumns = {
            @JoinColumn(name = "id_person_father_and_mother", referencedColumnName = "id")
    })
    private List<Person> adoptiveChildren;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "TB_ADOPTIVE_PARENTS",
            joinColumns = {@JoinColumn(name = "id_person_adoptive_fk")},
            inverseJoinColumns = {@JoinColumn(name = "id_person_father_adoptive_fk")})
    private List<Person> adoptiveParents;

    public Person(String name, Boolean isActive){
        this.name = name;
        this.isActive = isActive;
    }

}
