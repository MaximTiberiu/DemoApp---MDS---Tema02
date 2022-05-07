package com.tiberiumaxim.demoaplicatietema02.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("vendorUrl")
    private String vendorUrl;
}
