package com.example.genealogictree.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "dto create genealogic tree")
public class CreateGenealogicTreeDto {

    @ApiModelProperty(value = "name genealogic tree", example = "My Family", required = true)
    private String name;
    @ApiModelProperty(value = "id account user", example = "1", required = true)
    private Integer idAccount;
}
