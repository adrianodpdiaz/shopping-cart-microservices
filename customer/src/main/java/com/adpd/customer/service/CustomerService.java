package com.adpd.customer.service;

import com.adpd.amqp.constants.ExchangeQueueConstants;
import com.adpd.amqp.producer.RabbitMQMessageProducer;
import com.adpd.customer.entity.Address;
import com.adpd.customer.mapping.AddressMapper;
import com.adpd.customer.mapping.CustomerMapper;
import com.adpd.customer.repository.AddressRepository;
import com.adpd.customer.resource.form.RegisterAddressForm;
import com.adpd.feignclients.resource.dto.AddressDTO;
import com.adpd.feignclients.resource.dto.CustomerDTO;
import com.adpd.customer.resource.form.RegisterCustomerForm;
import com.adpd.customer.entity.Customer;
import com.adpd.customer.repository.CustomerRepository;
import com.adpd.feignclients.resource.form.SendNotificationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private static final String NOTIFICATION_SENDER = "Customer-Service-App";
    private static final String NOTIFICATION_MSG = "Customer Registered";

    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public Long registerCustomer(RegisterCustomerForm registerCustomerForm, String userEmail) {
        Customer customer = customerMapper.requestToEntity(registerCustomerForm);
        customerRepository.saveAndFlush(customer);

        if (!registerCustomerForm.getEmail().equals(userEmail)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        SendNotificationForm sendNotificationForm = new SendNotificationForm();
        sendNotificationForm.setToCustomerId(customer.getId());
        sendNotificationForm.setToCustomerEmail(customer.getEmail());
        sendNotificationForm.setSender(NOTIFICATION_SENDER);
        sendNotificationForm.setMessage(NOTIFICATION_MSG);

        rabbitMQMessageProducer.publish(ExchangeQueueConstants.INTERNAL_EXCHANGE,
            ExchangeQueueConstants.INTERNAL_NOTIFICATION_ROUTING_KEY, sendNotificationForm);

        return customer.getId();
    }

    public CustomerDTO getCustomer(Long id) {
        Customer customer = findCustomer(id);
        return customerMapper.toDTO(customer);
    }

    public void createAddress(Long customerId, RegisterAddressForm registerAddressForm) {
        Customer customer = findCustomer(customerId);
        Address address = addressMapper.requestToEntity(registerAddressForm);
        address.setCustomer(customer);
        addressRepository.save(address);
    }

    private Customer findCustomer(Long id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                String.format("Register not found for customer with id: %d", id)));
    }

    public List<AddressDTO> listAddresses(Long customerId) {
        List<Address> addresses = addressRepository.findAllByCustomerId(customerId);
        return addressMapper.toDTOList(addresses);
    }
}
