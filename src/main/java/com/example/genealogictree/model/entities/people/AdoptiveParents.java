package com.example.genealogictree.model.entities.people;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Setter
@Getter
public class AdoptiveParents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "adoptiveParents", fetch = FetchType.EAGER)
    private List<People> adoptiveParents = new ArrayList<People>();

    public AdoptiveParents(){}

    public AdoptiveParents(People parent1, People parent2){
        adoptiveParents = new ArrayList<People>();
        adoptiveParents.addAll(List.of(parent1,parent2));
    }
}
