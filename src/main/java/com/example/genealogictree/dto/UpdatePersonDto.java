package com.example.genealogictree.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdatePersonDto {

    private Integer idPerson;
    private String name;
    private LocalDate birth;
    private Boolean isActive;
    private String image;
}
