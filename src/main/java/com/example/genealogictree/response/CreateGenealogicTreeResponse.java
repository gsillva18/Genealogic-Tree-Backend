package com.example.genealogictree.response;

import com.example.genealogictree.model.entitygenealogictree.Person;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "response create genealogic tree")
public class CreateGenealogicTreeResponse {

    @ApiModelProperty(value = "id genealogic tree", example = "1", required = true)
    private Integer idGenealogicTree;
    @ApiModelProperty(value = "name genealogic tree", example = "My Family", required = true)
    private String nameGenealogicTree;
    @ApiModelProperty(value = "inital Person", example = "{Person.class}", required = true)
    private Person initialPerson;
}
