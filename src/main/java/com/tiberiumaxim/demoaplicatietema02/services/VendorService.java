package com.tiberiumaxim.demoaplicatietema02.services;

import com.tiberiumaxim.demoaplicatietema02.api.v1.model.VendorDTO;
import com.tiberiumaxim.demoaplicatietema02.api.v1.model.VendorListDTO;

public interface VendorService {

    VendorDTO getVendorById(Long id);

    VendorListDTO getAllVendors();

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveByVendorDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
