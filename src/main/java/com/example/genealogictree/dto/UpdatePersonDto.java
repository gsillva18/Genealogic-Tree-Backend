package com.example.genealogictree.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel(description = "dto update person")
public class UpdatePersonDto {

    @ApiModelProperty(value = "id person", example = "4", required = true)
    private Integer idPerson;
    @ApiModelProperty(value = "name person", example = "user 4 update")
    private String name;
    @ApiModelProperty(value = "birth person", example = "2001-09-03")
    private LocalDate birth;
    @ApiModelProperty(value = "is active person", example = "true")
    private Boolean isActive;
    @ApiModelProperty(value = "image person", example = "bfwofgyeqogfyweoiqgfeiwofgeiwoqfeywofgeywoqfe")
    private String image;
}
