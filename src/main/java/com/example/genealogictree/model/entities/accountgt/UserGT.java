package com.example.genealogictree.model.entities.accountgt;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;

@EqualsAndHashCode
@Embeddable
@ToString
@Data
public class UserGT {

    private String name;

}
