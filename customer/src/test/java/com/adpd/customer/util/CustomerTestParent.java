package com.adpd.customer.util;

import com.adpd.customer.entity.Address;
import com.adpd.customer.entity.Customer;
import com.adpd.customer.resource.form.RegisterCustomerForm;
import com.adpd.feignclients.resource.dto.CustomerDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class CustomerTestParent {

    protected static final String TEST_EMAIL = "test@test.com";
    protected static final String CUSTOMER_FIRST_NAME = "First";
    protected static final String CUSTOMER_LAST_NAME = "Last";
    protected static final LocalDate CUSTOMER_BIRTH_DATE = LocalDate.of(1990, 5, 27);

    protected static final String STREET_ADDRESS = "address";
    protected static final String CITY = "Budapest";
    protected static final String COUNTRY = "Hungary";
    protected static final String ZIP_CODE = "0000";

    public static RegisterCustomerForm mockRegisterCustomerForm() {
        RegisterCustomerForm registerCustomerForm = new RegisterCustomerForm();
        registerCustomerForm.setFirstName(CUSTOMER_FIRST_NAME);
        registerCustomerForm.setLastName(CUSTOMER_LAST_NAME);
        registerCustomerForm.setEmail(TEST_EMAIL);
        registerCustomerForm.setBirthDate(CUSTOMER_BIRTH_DATE);
        return registerCustomerForm;
    }

    public static Customer mockCustomer(Long id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(CUSTOMER_FIRST_NAME);
        customer.setLastName(CUSTOMER_LAST_NAME);
        customer.setEmail(TEST_EMAIL);
        customer.setBirthDate(CUSTOMER_BIRTH_DATE);
        return customer;
    }

    public static CustomerDTO mockCustomerDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(CUSTOMER_FIRST_NAME);
        customerDTO.setLastName(CUSTOMER_LAST_NAME);
        customerDTO.setEmail(TEST_EMAIL);
        customerDTO.setBirthDate(CUSTOMER_BIRTH_DATE);
        return customerDTO;
    }

    public static Address mockAddress(Long id) {
        Address address = new Address();
        address.setId(id);
        address.setStreetAddress(STREET_ADDRESS);
        address.setCity(CITY);
        address.setState(CITY);
        address.setCountry(COUNTRY);
        address.setZipCode(ZIP_CODE);
        return address;
    }
}
