package com.adpd.feignclients.client;

import com.adpd.feignclients.config.ErrorDecoderConfig;
import com.adpd.feignclients.resource.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customer", path = "/api/v1/customers",
        configuration = ErrorDecoderConfig.class)
public interface CustomerClient {

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Long id);

}
