package com.example.genealogictree.response;

import com.example.genealogictree.model.entitygenealogictree.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateGenealogicTreeResponse {

    private Integer idGenealogicTree;
    private String nameGenealogicTree;
    private Person initialPerson;
}
