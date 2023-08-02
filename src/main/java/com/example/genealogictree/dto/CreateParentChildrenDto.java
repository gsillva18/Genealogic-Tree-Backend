package com.example.genealogictree.dto;

import com.example.genealogictree.core.enums.TypePerson;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "dto create parent or children")
public class CreateParentChildrenDto {

    @ApiModelProperty(value = "id person for add parent or children", example = "1", required = true)
    private Integer idPerson;
    @ApiModelProperty(value = "id parent or children existing for add in the person", example = "5")
    private Integer idParentExisting;
    @ApiModelProperty(value = "type person for add in system", example = "BIOLOGICAL_CHILDREN", required = true)
    private TypePerson typePerson;
    @ApiModelProperty(value = "type parent if type person is BIOLOGICAL_CHILDREN", example = "BIOLOGICAL_FATHER")
    private TypePerson typeParent;
    @ApiModelProperty(value = "name person if idParentExisting does not exist", example = "user parent or children")
    private String name;
}
