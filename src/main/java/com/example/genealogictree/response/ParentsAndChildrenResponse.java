package com.example.genealogictree.response;

import com.example.genealogictree.core.enums.TypePerson;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "response parents or children")
public class ParentsAndChildrenResponse {

    @ApiModelProperty(value = "id person parent or children", example = "4", required = true)
    private Integer idPerson;
    @ApiModelProperty(value = "name", example = "user 4", required = true)
    private String name;
    @ApiModelProperty(value = "type person", example = "BIOLOGICAL_CHILDREN", required = true)
    private TypePerson type;
}
