package com.adpd.customer.mapping;

import com.adpd.customer.entity.Customer;
import com.adpd.customer.resource.form.RegisterCustomerForm;
import com.adpd.customer.util.CustomerTestParent;
import com.adpd.feignclients.resource.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
class CustomerMapperTest extends CustomerTestParent {

    @Test
    void testNullMappings() {
        Customer customer = CustomerMapper.INSTANCE.requestToEntity(null);
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.toDTO(null);

        assertNull(customer);
        assertNull(customerDTO);
    }

    @Test
    void testMappingFormToEntity() {
        RegisterCustomerForm registerCustomerForm = mockRegisterCustomerForm();

        Customer customer = CustomerMapper.INSTANCE.requestToEntity(registerCustomerForm);

        assertEquals(registerCustomerForm.getFirstName(), customer.getFirstName());
        assertEquals(registerCustomerForm.getLastName(), customer.getLastName());
        assertEquals(registerCustomerForm.getEmail(), customer.getEmail());
        assertEquals(registerCustomerForm.getBirthDate(), customer.getBirthDate());
    }

    @Test
    void testMappingEntityToDTO() {
        Customer customer = mockCustomer(1L);

        CustomerDTO customerDTO = CustomerMapper.INSTANCE.toDTO(customer);

        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getLastName(), customerDTO.getLastName());
        assertEquals(customer.getEmail(), customerDTO.getEmail());
        assertEquals(customer.getBirthDate(), customerDTO.getBirthDate());
    }
}
