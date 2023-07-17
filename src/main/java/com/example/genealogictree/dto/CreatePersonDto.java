package com.example.genealogictree.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CreatePersonDto {

    private String name;
    private LocalDate birth;
    private Boolean isActive;
    private String image;
    private List<ParentsAndChildrenDto> listParents;
    private List<ParentsAndChildrenDto> listChildren;

}
