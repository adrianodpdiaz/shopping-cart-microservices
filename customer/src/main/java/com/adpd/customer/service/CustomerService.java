package com.adpd.customer.service;

import com.adpd.customer.mapping.CustomerMapper;
import com.adpd.customer.resource.CustomerDTO;
import com.adpd.customer.resource.RegisterCustomerRequest;
import com.adpd.customer.entity.Customer;
import com.adpd.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Integer registerCustomer(RegisterCustomerRequest registerCustomerRequest) {
        Customer customer = customerMapper.requestToEntity(registerCustomerRequest);
        customerRepository.saveAndFlush(customer);
        return customer.getId();
    }

    public CustomerDTO getCustomer(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Register not found for customer with id: %d", id)));
        return customerMapper.toDTO(customer);
    }
}
