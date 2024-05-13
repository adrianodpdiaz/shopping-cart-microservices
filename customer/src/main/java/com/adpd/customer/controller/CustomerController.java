package com.adpd.customer.controller;

import com.adpd.customer.resource.form.RegisterCustomerForm;
import com.adpd.customer.resource.dto.CustomerDTO;
import com.adpd.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@Tag(name = "Customer")
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "Register new customer",
            description = "Post endpoint to register a new customer."
    )
    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> registerCustomer(@Valid @RequestBody RegisterCustomerForm registerCustomerForm) {
        Long customerId = customerService.registerCustomer(registerCustomerForm);
        log.info("registered customer {}", customerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get customer by id",
            description = "Get endpoint to get a customer's information."
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Long id) {
        CustomerDTO customer = customerService.getCustomer(id);
        log.info("retrieved customer {}", id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
