package com.example.genealogictree.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel(description = "response list genealogic tree")
public class ListGenealogicTreeResponse {

    @ApiModelProperty(value = "id genealogic tree", example = "1", required = true)
    private Integer idGenealogicTree;
    @ApiModelProperty(value = "name genealogic tree", example = "My Family", required = true)
    private String name;
    @ApiModelProperty(value = "creation date", example = "2023-08-02", required = true)
    private LocalDate creationDate;
}
