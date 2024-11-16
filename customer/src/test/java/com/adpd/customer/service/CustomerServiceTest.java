package com.adpd.customer.service;

import com.adpd.amqp.producer.RabbitMQMessageProducer;
import com.adpd.customer.entity.Customer;
import com.adpd.customer.mapping.CustomerMapper;
import com.adpd.customer.repository.CustomerRepository;
import com.adpd.customer.resource.form.RegisterCustomerForm;
import com.adpd.customer.util.CustomerTestParent;
import com.adpd.feignclients.resource.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest extends CustomerTestParent {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private RabbitMQMessageProducer rabbitMQMessageProducer;
    @InjectMocks
    private CustomerService customerService;


    @Test
    void shouldRegisterACustomer() {
        RegisterCustomerForm registerCustomerForm = mockRegisterCustomerForm();
        Customer customer = mockCustomer(1L);

        doNothing().when(rabbitMQMessageProducer).publish(any(), any(), any());
        when(customerMapper.requestToEntity(any())).thenReturn(customer);
        when(customerRepository.saveAndFlush(customer)).thenReturn(customer);

        Long customerId = customerService.registerCustomer(registerCustomerForm, TEST_EMAIL);

        assertEquals(registerCustomerForm.getFirstName(), customer.getFirstName());
        assertEquals(registerCustomerForm.getLastName(), customer.getLastName());
        assertEquals(registerCustomerForm.getEmail(), customer.getEmail());
        assertEquals(registerCustomerForm.getBirthDate(), customer.getBirthDate());
        assertEquals(1L, customerId);
    }

    @Test
    void shouldGetACustomer() {
        Long customerId = 1L;
        Customer customer = mockCustomer(customerId);
        CustomerDTO mockCustomerDTO = mockCustomerDTO();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(mockCustomerDTO);

        CustomerDTO customerDTO = customerService.getCustomer(customerId);
        assertEquals(mockCustomerDTO.getFirstName(), customerDTO.getFirstName());
        assertEquals(mockCustomerDTO.getLastName(), customerDTO.getLastName());
        assertEquals(mockCustomerDTO.getEmail(), customerDTO.getEmail());
        assertEquals(mockCustomerDTO.getBirthDate(), customerDTO.getBirthDate());
    }
}
