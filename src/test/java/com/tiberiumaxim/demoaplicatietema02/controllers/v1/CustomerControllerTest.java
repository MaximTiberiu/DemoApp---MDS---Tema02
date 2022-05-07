package com.tiberiumaxim.demoaplicatietema02.controllers.v1;

import com.tiberiumaxim.demoaplicatietema02.api.v1.model.CustomerDTO;
import com.tiberiumaxim.demoaplicatietema02.services.CustomerService;
import com.tiberiumaxim.demoaplicatietema02.services.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.tiberiumaxim.demoaplicatietema02.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    public static final String FIRSTNAME1 = "FirstName1";
    public static final String FIRSTNAME2 = "FirstName2";
    public static final String CUSTOMERURL1 = "/api/v1/customers/1";

    public static final String LASTNAME1 = "LastName1";
    public static final String LASTNAME2 = "LastName2";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void testListCustomers()  throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setFirstName(FIRSTNAME1);
        customerDTO1.setLastName(LASTNAME1);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstName(FIRSTNAME2);
        customerDTO2.setLastName(LASTNAME2);

        List<CustomerDTO> customerDTOS = Arrays.asList(customerDTO1, customerDTO2);

        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerDTOS", hasSize(2)));
    }

    @Test
    void testGetCustomerById() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRSTNAME1);
        customerDTO.setLastName(LASTNAME1);
        customerDTO.setCustomerUrl(CUSTOMERURL1);

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get(CUSTOMERURL1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRSTNAME1)));
    }

    @Test
    void testCreateNewCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRSTNAME1);
        customerDTO.setLastName(LASTNAME1);

        CustomerDTO returnedCustomerDTO = new CustomerDTO();
        returnedCustomerDTO.setFirstName(customerDTO.getFirstName());
        returnedCustomerDTO.setLastName(customerDTO.getLastName());
        returnedCustomerDTO.setCustomerUrl(CUSTOMERURL1);

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnedCustomerDTO);

        mockMvc.perform(post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRSTNAME1)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMERURL1)));
    }

    @Test
    void testUpdateCustomer() throws Exception {

        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRSTNAME1);
        customerDTO.setLastName(LASTNAME1);

        CustomerDTO returnedCustomerDTO = new CustomerDTO();
        returnedCustomerDTO.setFirstName(customerDTO.getFirstName());
        returnedCustomerDTO.setLastName(customerDTO.getLastName());
        returnedCustomerDTO.setCustomerUrl(CUSTOMERURL1);

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnedCustomerDTO);

        mockMvc.perform(put(CUSTOMERURL1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRSTNAME1)))
                .andExpect(jsonPath("$.lastName", equalTo(LASTNAME1)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMERURL1)));
    }

    @Test
    void testPatchCustomer() throws Exception {

        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRSTNAME1);

        CustomerDTO returnedCustomerDTO = new CustomerDTO();
        returnedCustomerDTO.setFirstName(customerDTO.getFirstName());
        returnedCustomerDTO.setLastName(LASTNAME1);
        returnedCustomerDTO.setCustomerUrl(CUSTOMERURL1);

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnedCustomerDTO);

        mockMvc.perform(patch(CUSTOMERURL1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRSTNAME1)))
                .andExpect(jsonPath("$.lastName", equalTo(LASTNAME1)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMERURL1)));
    }

    @Test
    void testDeleteCustomer() throws Exception {

        mockMvc.perform(delete(CUSTOMERURL1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }

    @Test
    void testGetByNameNotFound() throws Exception {

        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}