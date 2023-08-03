package com.example.genealogictree.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "dto delete person")
public class DeletePersonDto {

    @ApiModelProperty(value = "id person", example = "4", required = true)
    private Integer idPerson;
}
