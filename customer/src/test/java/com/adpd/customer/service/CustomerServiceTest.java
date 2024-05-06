package com.adpd.customer.service;

import com.adpd.amqp.producer.RabbitMQMessageProducer;
import com.adpd.customer.entity.Customer;
import com.adpd.customer.mapping.CustomerMapperImpl;
import com.adpd.customer.repository.CustomerRepository;
import com.adpd.customer.resource.form.RegisterCustomerForm;
import com.adpd.customer.resource.outbound.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.adpd.customer.util.CustomerTestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapperImpl customerMapper;
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

        Long customerId = customerService.registerCustomer(registerCustomerForm);
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
