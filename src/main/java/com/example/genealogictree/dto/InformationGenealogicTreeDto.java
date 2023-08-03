package com.example.genealogictree.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "dto information genealogic tree")
public class InformationGenealogicTreeDto {

    @ApiModelProperty(value = "id genealogic tree", example = "1", required = true)
    private Integer idGenealogicTree;
}
