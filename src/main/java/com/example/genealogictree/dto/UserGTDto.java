package com.example.genealogictree.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "dto create user")
public class UserGTDto {

    @ApiModelProperty(value = "id user Auth0", example = "bdye79rewu8", required = true)
    private String idUserAuth0;

}
