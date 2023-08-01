package com.example.genealogictree.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PersonResponse {

    private Integer id;
    private String name;
    private LocalDate birth;
    private Boolean isActive;
    private String image;
    private Integer layer;
    private List<ParentsAndChildrenResponse> listParents;
    private List<ParentsAndChildrenResponse> listChildren;
}
