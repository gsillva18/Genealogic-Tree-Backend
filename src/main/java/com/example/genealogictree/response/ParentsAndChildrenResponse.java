package com.example.genealogictree.response;

import com.example.genealogictree.core.enums.TypePerson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParentsAndChildrenResponse {

    private Integer idPerson;
    private String name;
    private TypePerson type;
}
