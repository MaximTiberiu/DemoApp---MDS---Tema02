package com.tiberiumaxim.demoaplicatietema02.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryDTO {

    @JsonProperty("id")
    private Long id;

    @ApiModelProperty(value = "Category Title")
    @JsonProperty("name")
    private String name;
}
