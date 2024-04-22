package com.adpd.feignclients.customer;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomerClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 400) {
            return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found");
        }
        if (response.status() >= 400 && response.status() <= 499) {
            return new ResponseStatusException(
                    HttpStatus.valueOf(response.status()), "Customer-service bad request");
        } else if (response.status() >= 500) {
            return new ResponseStatusException(
                    HttpStatus.valueOf(response.status()), "Customer-service server error");
        }
        return new Exception("Exception while getting customer details");
    }
}
