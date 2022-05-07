package com.tiberiumaxim.demoaplicatietema02.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorListDTO {

    @JsonProperty("vendorDTOS")
    private List<VendorDTO> vendorDTOS;
}
