package com.example.genealogictree.dto;

import com.example.genealogictree.core.enums.TypePerson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletePersonDto {

    private Integer idPersonDelete;
    private Integer idPerson;
    private TypePerson typePersonDeleted;
}
