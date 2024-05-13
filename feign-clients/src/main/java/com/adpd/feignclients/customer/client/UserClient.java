package com.adpd.feignclients.customer.client;

import com.adpd.feignclients.config.ErrorDecoderConfig;
import com.adpd.feignclients.customer.resource.dto.UserDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user", path = "/api/v1/users",
        configuration = ErrorDecoderConfig.class)
public interface UserClient {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDetailsDTO> getUser(@RequestParam("email") String email);

}
