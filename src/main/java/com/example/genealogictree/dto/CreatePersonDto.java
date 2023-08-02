package com.example.genealogictree.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "dto create person")
public class CreatePersonDto {

    @ApiModelProperty(value = "id genealogic tree", example = "1", required = true)
    private Integer idGenealogicTree;
    @ApiModelProperty(value = "name initial person", example = "user 1", required = true)
    private String name;

}
