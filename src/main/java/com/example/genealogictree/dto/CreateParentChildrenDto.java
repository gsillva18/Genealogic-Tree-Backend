package com.example.genealogictree.dto;

import com.example.genealogictree.core.enums.TypePerson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateParentChildrenDto {

    private Integer idPerson;
    private TypePerson typePerson;
    private TypePerson typeParent;
    private String name;
}
