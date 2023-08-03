package com.example.genealogictree.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "dto get list genealogic tree")
public class ListGenealogicTreeDto {

    @ApiModelProperty(value = "id account ", example = "1", required = true)
    private Integer idAccount;
}
