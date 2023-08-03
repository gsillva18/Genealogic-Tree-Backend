package com.example.genealogictree.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ApiModel(description = "response person")
public class PersonResponse {

    @ApiModelProperty(value = "id person", example = "1", required = true)
    private Integer id;
    @ApiModelProperty(value = "name", example = "user 1", required = true)
    private String name;
    @ApiModelProperty(value = "birth", example = "2001-09-08", required = true)
    private LocalDate birth;
    @ApiModelProperty(value = "is active", example = "true", required = true)
    private Boolean isActive;
    @ApiModelProperty(value = "image", example = "guwifgruefeyuwfgwyfeywufgewyufyggwefgyu", required = true)
    private String image;
    @ApiModelProperty(value = "layer", example = "0", required = true)
    private Integer layer;
    @ApiModelProperty(value = "list parents", required = true)
    private List<ParentsAndChildrenResponse> listParents;
    @ApiModelProperty(value = "list children", required = true)
    private List<ParentsAndChildrenResponse> listChildren;
}
