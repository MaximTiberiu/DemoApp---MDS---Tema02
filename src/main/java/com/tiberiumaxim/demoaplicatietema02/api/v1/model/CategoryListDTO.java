package com.tiberiumaxim.demoaplicatietema02.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListDTO {

    @JsonProperty("categoryDTOS")
    private List<CategoryDTO> categoryDTOS;
}
