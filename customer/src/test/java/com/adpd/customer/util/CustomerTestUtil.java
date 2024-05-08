package com.adpd.customer.util;

import com.adpd.customer.entity.Customer;
import com.adpd.customer.resource.form.RegisterCustomerForm;
import com.adpd.customer.resource.outbound.CustomerDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerTestUtil {

    private static final String CUSTOMER_FIRST_NAME = "First";
    private static final String CUSTOMER_LAST_NAME = "Last";
    private static final String CUSTOMER_EMAIL = "test@test.com";
    private static final LocalDate CUSTOMER_BIRTH_DATE = LocalDate.of(1990, 5, 27);

    public static RegisterCustomerForm mockRegisterCustomerForm() {
        RegisterCustomerForm registerCustomerForm = new RegisterCustomerForm();
        registerCustomerForm.setFirstName(CUSTOMER_FIRST_NAME);
        registerCustomerForm.setLastName(CUSTOMER_LAST_NAME);
        registerCustomerForm.setEmail(CUSTOMER_EMAIL);
        registerCustomerForm.setBirthDate(CUSTOMER_BIRTH_DATE);
        return registerCustomerForm;
    }

    public static Customer mockCustomer(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(CUSTOMER_FIRST_NAME);
        customer.setLastName(CUSTOMER_LAST_NAME);
        customer.setEmail(CUSTOMER_EMAIL);
        customer.setBirthDate(CUSTOMER_BIRTH_DATE);
        return customer;
    }

    public static CustomerDTO mockCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(CUSTOMER_FIRST_NAME);
        customerDTO.setLastName(CUSTOMER_LAST_NAME);
        customerDTO.setEmail(CUSTOMER_EMAIL);
        customerDTO.setBirthDate(CUSTOMER_BIRTH_DATE);
        return customerDTO;
    }
}
