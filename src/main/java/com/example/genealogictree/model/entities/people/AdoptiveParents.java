package com.example.genealogictree.model.entities.people;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Setter
@Getter
@Embeddable
public class AdoptiveParents {

    private List<People> adoptiveParents = new ArrayList<People>();

    public AdoptiveParents(){}

    public AdoptiveParents(People parent1, People parent2){
        adoptiveParents = new ArrayList<People>();
        adoptiveParents.addAll(List.of(parent1,parent2));
    }
}
