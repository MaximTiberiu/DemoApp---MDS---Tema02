package com.tiberiumaxim.demoaplicatietema02.api.v1.mapper;

import com.tiberiumaxim.demoaplicatietema02.api.v1.model.CustomerDTO;
import com.tiberiumaxim.demoaplicatietema02.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    private static final String FIRSTNAME = "FirstName";
    private static final String LASTNAME = "LastName";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    void customerToCustomerDTO() {
        // given
        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        // when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        // then
        assertEquals(FIRSTNAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());
    }

    @Test
    void customerDTOToCustomer() {

        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRSTNAME);
        customerDTO.setLastName(LASTNAME);

        // when
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        // then
        assertEquals(FIRSTNAME, customer.getFirstName());
        assertEquals(LASTNAME, customer.getLastName());
    }
}