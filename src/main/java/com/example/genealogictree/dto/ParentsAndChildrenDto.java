package com.example.genealogictree.dto;

import com.example.genealogictree.core.enums.TypePerson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentsAndChildrenDto {

    private Integer idPerson;
    private String name;
    private TypePerson type;
}
