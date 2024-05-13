package com.adpd.customer.mapping;

import com.adpd.customer.entity.Customer;
import com.adpd.customer.resource.form.RegisterCustomerForm;
import com.adpd.customer.resource.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.adpd.customer.util.CustomerTestUtil.mockCustomer;
import static com.adpd.customer.util.CustomerTestUtil.mockRegisterCustomerForm;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class CustomerMapperTest {

    @InjectMocks
    private CustomerMapperImpl customerMapper;

    @Test
    void testNullMappings() {
        Customer customer = customerMapper.requestToEntity(null);
        CustomerDTO customerDTO = customerMapper.toDTO(null);

        assertNull(customer);
        assertNull(customerDTO);
    }

    @Test
    void testMappingFormToEntity() {
        RegisterCustomerForm registerCustomerForm = mockRegisterCustomerForm();

        Customer customer = customerMapper.requestToEntity(registerCustomerForm);

        assertEquals(registerCustomerForm.getFirstName(), customer.getFirstName());
        assertEquals(registerCustomerForm.getLastName(), customer.getLastName());
        assertEquals(registerCustomerForm.getEmail(), customer.getEmail());
        assertEquals(registerCustomerForm.getBirthDate(), customer.getBirthDate());
    }

    @Test
    void testMappingEntityToDTO() {
        Customer customer = mockCustomer(1L);

        CustomerDTO customerDTO = customerMapper.toDTO(customer);

        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getLastName(), customerDTO.getLastName());
        assertEquals(customer.getEmail(), customerDTO.getEmail());
        assertEquals(customer.getBirthDate(), customerDTO.getBirthDate());
    }
}
