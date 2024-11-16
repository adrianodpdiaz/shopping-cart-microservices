package com.adpd.customer.controller;

import com.adpd.customer.resource.form.RegisterCustomerForm;
import com.adpd.customer.service.CustomerService;
import com.adpd.customer.util.CustomerTestParent;
import com.adpd.customer.util.LocalDateTypeAdapter;
import com.adpd.feignclients.resource.dto.CustomerDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest extends CustomerTestParent {

    private static final String URL_CUSTOMER = "/api/v1/customers/1";
    private static final String URL_CUSTOMERS = "/api/v1/customers";

    @Inject
    private MockMvc mockMvc;
    @Inject
    private Gson gson;
    @MockBean
    private CustomerService customerService;

    @BeforeEach
    void setup() {
        gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .create();
    }

    @Test
    void shouldRegisterACustomer() throws Exception {
        RegisterCustomerForm registerCustomerForm = mockRegisterCustomerForm();
        String payload = gson.toJson(registerCustomerForm);

        when(customerService.registerCustomer(any(), any())).thenReturn(1L);

        mockMvc.perform(post(URL_CUSTOMERS).content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$").doesNotExist());
    }


    @Test
    void shouldGetACustomer() throws Exception {
        CustomerDTO customerDTO = mockCustomerDTO();

        when(customerService.getCustomer(any())).thenReturn(customerDTO);

        mockMvc.perform(get(URL_CUSTOMER).accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.firstName").value(customerDTO.getFirstName()))
            .andExpect(jsonPath("$.lastName").value(customerDTO.getLastName()))
            .andExpect(jsonPath("$.email").value(customerDTO.getEmail()))
            .andExpect(jsonPath("$.birthDate").value(customerDTO.getBirthDate().toString()));
    }

    @Test
    void testRegisterCustomerFormValidation() throws Exception {
        String invalidEmail = "invalid.email@invalid";
        RegisterCustomerForm registerCustomerForm = new RegisterCustomerForm();
        registerCustomerForm.setEmail(invalidEmail);
        registerCustomerForm.setBirthDate(LocalDate.of(2050, 1, 1));
        String payload = gson.toJson(registerCustomerForm);

        when(customerService.registerCustomer(registerCustomerForm, invalidEmail)).thenReturn(1L);

        mockMvc.perform(post(URL_CUSTOMERS).content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.errors", hasItem("first name is required")))
            .andExpect(jsonPath("$.errors", hasItem("last name is required")))
            .andExpect(jsonPath("$.errors", hasItem("must be a well-formed email address")))
            .andExpect(jsonPath("$.errors", hasItem("must be a past date")));
    }
}
