package com.adpd.customer.service;

import com.adpd.amqp.constants.ExchangeQueueConstants;
import com.adpd.amqp.producer.RabbitMQMessageProducer;
import com.adpd.customer.mapping.CustomerMapper;
import com.adpd.customer.resource.dto.CustomerDTO;
import com.adpd.customer.resource.form.RegisterCustomerForm;
import com.adpd.customer.entity.Customer;
import com.adpd.customer.repository.CustomerRepository;
import com.adpd.feignclients.resource.form.SendNotificationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private static final String NOTIFICATION_SENDER = "Customer-Service-App";
    private static final String NOTIFICATION_MSG = "Customer Registered.";

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public Long registerCustomer(RegisterCustomerForm registerCustomerForm) {
        Customer customer = customerMapper.requestToEntity(registerCustomerForm);
        customerRepository.saveAndFlush(customer);

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
        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                String.format("Register not found for customer with id: %d", id)));
        return customerMapper.toDTO(customer);
    }
}
