package com.example.genealogictree.model.entities.genealogictree;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Setter
@Getter
@Table(name = "genealogies")
public class GenealogicTree {

    public GenealogicTree(){
        creationDate = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "DATE")
    private LocalDate creationDate;
}
