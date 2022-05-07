package com.tiberiumaxim.demoaplicatietema02.services;

import com.tiberiumaxim.demoaplicatietema02.api.v1.mapper.VendorMapper;
import com.tiberiumaxim.demoaplicatietema02.api.v1.model.VendorDTO;
import com.tiberiumaxim.demoaplicatietema02.api.v1.model.VendorListDTO;
import com.tiberiumaxim.demoaplicatietema02.domain.Vendor;
import com.tiberiumaxim.demoaplicatietema02.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class VendorServiceTest {

    private static final String NAME1 = "Name1";
    private static final Long ID1 = 1L;

    private static final String NAME2 = "Name2";
    private static final Long ID2 = 2L;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    void getVendorById() {

        // given
        Vendor vendor = getVendor1();

        // mockito BDD syntax
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        // when
        VendorDTO vendorDTO = vendorService.getVendorById(ID1);

        // then
        then(vendorRepository).should(times(1)).findById(anyLong());

        // JUnit Assert that with matchers
        assertThat(vendorDTO.getName(), is(equalTo(NAME1)));
    }

    @Test
    void getAllVendors() {

        // given
        List<Vendor> vendors = Arrays.asList(getVendor1(), getVendor2());
        given(vendorRepository.findAll()).willReturn(vendors);

        // when
        VendorListDTO vendorListDTO = vendorService.getAllVendors();

        // then
        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorListDTO.getVendorDTOS().size(), is(equalTo(2)));
    }

    @Test
    void createNewVendor() {

        // given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME1);

        Vendor vendor = new Vendor();

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        // when
        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

        // then
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    void saveByVendorDTO() {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME1);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        //when
        VendorDTO savedVendorDTO = vendorService.saveByVendorDTO(ID1, vendorDTO);

        //then
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    void patchVendor() {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME1);

        Vendor vendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        //when
        VendorDTO savedVendorDTO = vendorService.patchVendor(ID1, vendorDTO);

        //then
        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    void deleteVendorById() {

        //when
        vendorService.deleteVendorById(1L);

        //then
        then(vendorRepository).should().deleteById(anyLong());
    }

    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME1);
        vendor.setId(ID1);
        return vendor;
    }

    private Vendor getVendor2() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME2);
        vendor.setId(ID2);
        return vendor;
    }
}