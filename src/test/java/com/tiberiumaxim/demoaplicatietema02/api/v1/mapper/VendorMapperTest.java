package com.tiberiumaxim.demoaplicatietema02.api.v1.mapper;

import com.tiberiumaxim.demoaplicatietema02.api.v1.model.VendorDTO;
import com.tiberiumaxim.demoaplicatietema02.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VendorMapperTest {

    private static final String NAME = "Name";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    void vendorToVendorDTO() {

        // given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        // when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        // then
        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    void vendorDTOToVendor() {

        // given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        // when
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        // then
        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}