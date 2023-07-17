package com.example.genealogictree.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateGenealogicTreeDto {

    private String name;
    private Integer idAccount;
}
