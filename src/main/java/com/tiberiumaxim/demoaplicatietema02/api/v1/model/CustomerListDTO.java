package com.tiberiumaxim.demoaplicatietema02.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerListDTO {

    @JsonProperty("customerDTOS")
    private List<CustomerDTO> customerDTOS;
}
