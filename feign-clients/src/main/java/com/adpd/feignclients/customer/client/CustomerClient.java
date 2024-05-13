package com.adpd.feignclients.customer.client;

import com.adpd.feignclients.config.ErrorDecoderConfig;
import com.adpd.feignclients.customer.resource.dto.CustomerDTO;
import com.adpd.feignclients.customer.resource.dto.UserDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "customer", path = "/api/v1",
        configuration = ErrorDecoderConfig.class)
public interface CustomerClient {

    @GetMapping(value = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Long id);

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDetailsDTO> getUser(@RequestParam("email") String email);

}
