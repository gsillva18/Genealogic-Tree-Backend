package com.example.genealogictree.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ListGenealogicTreeResponse {

    private Integer idGenealogicTree;
    private String name;
    private LocalDate criationDate;
}
