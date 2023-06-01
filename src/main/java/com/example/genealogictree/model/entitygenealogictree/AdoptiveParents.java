package com.example.genealogictree.model.entitygenealogictree;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "TB_ADOPTIVE_PARENTS")
public class AdoptiveParents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "adoptive_father_fk")
    private Person adoptiveFather;

    @ManyToOne
    @JoinColumn(name = "adoptive_mother_fk")
    private Person adoptiveMother;

}
