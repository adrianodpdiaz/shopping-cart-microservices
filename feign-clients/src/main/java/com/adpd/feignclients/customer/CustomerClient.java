package com.adpd.feignclients.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customer", path = "/api/v1/customers",
        configuration = CustomerClientConfig.class)
public interface CustomerClient {

    @GetMapping("/{id}")
    ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Long id);

}
